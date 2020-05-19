import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map, tap, concatMap } from "rxjs/operators";
import { HttpClient } from "@angular/common/http";
import { Equipment, Auth, Roles } from "../types";

@Injectable({
  providedIn: "root"
})
export class BackendService {
    static apiPath = "http://localhost:8080/";
    static eqPath = "equipment";
    static orderPath = "order";
    static touristPath = "tourist";
    static list = "list";
    static new = "new";
    static find = "find";
    static delete = "delete";
    static update = "update";
    static login = "login";

    constructor(private _http: HttpClient) {}

    private auth: Auth = {
        user: {
            id: null, 
            name: "", 
            email: "", 
            password: "", 
            role: Roles.client
        },
        token: ""
    };
    
    public isLoggedIn(): boolean {
        return !!this.auth.token;
    }

    public isAdmin(): boolean {
        return this.auth.user.role === Roles.admin;
    }

    public logout() {
        this.auth.token = "";
    }

    public login(email, password) {
        const url = `${BackendService.apiPath}${BackendService.touristPath}/${BackendService.login}/${email}/${password}`;
        return this._http.get(url,
            { headers: this.getBasicHeaders() }
        ).pipe(
            tap((res: Auth) => this.auth = res)
        );
    }

    private getBasicHeaders() {
        const headers = {
          'X-Requested-With': "", 
          'XMLHttpRequest': "",
          "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"
        };
        return headers;
    }
    
    private getAuthHeaders() {
        const headers = this.getBasicHeaders();
        if (this.isLoggedIn) {
          headers["Authorization"] = `Bearer ${this.auth.token}`;
        }
        return headers;
    }

    public register(name, email, password) {
        const url = `${BackendService.apiPath}${BackendService.touristPath}/${BackendService.new}/${name}/${email}/${password}`;
        return this._http.get(url,  
            { headers: this.getBasicHeaders() }
        ).pipe(
            tap((res: Auth) => this.auth = res)
        );
    }


    public getEquipmentList() {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${BackendService.list}`;
        return this._http.get(url,
          { headers: this.getBasicHeaders() }
        )
    }

    public getEquipmentDetails(id: string) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${id}`;
        return this._http.get(url,
          { headers: this.getBasicHeaders() }
        )
    }

    public addEquipment(name: string, type: string, price: number) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${BackendService.new}`;
        const data = new FormData();
        data.append("name", name);
        data.append("type", type);
        data.append("price", price.toString());
        return this._http.post(url, data,
            { headers: this.getAuthHeaders() },
        )
    }
    
    public deleteEquipment(id: number) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${BackendService.delete}/${id}`;
        return this._http.get(url,
          { headers: this.getAuthHeaders() }
        )
    }

    public updateEquipment(id: number, name: string, type: string, price: number) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${BackendService.update}/${id}`;
        return this.getEquipmentDetails(id.toString()).pipe(
            map((res: Equipment) => {
                const data = new FormData();
                data.append("name", name ? name : res.name);
                data.append("type", type ? type : res.type);
                data.append("price", price ? price.toString() : res.price.toString());
                return data;
            }),
            concatMap((data)=> 
                this._http.post(url, data,
            { headers: this.getAuthHeaders() })
            )
        );
    }

    public addOrder(equipmentId: string) {
        const url = `${BackendService.apiPath}${BackendService.orderPath}/${BackendService.new}`;
        const data = new FormData();
        data.append("touristId", this.auth.user.id.toString());
        data.append("equipmentId", equipmentId);
        return this._http.post(url, data,
            { headers: this.getAuthHeaders() },
        )
    }

    public getOrdersList() {
        const url = `${BackendService.apiPath}${BackendService.orderPath}/${BackendService.list}/${this.auth.user.id}`;
        return this._http.get(url,
            { headers: this.getAuthHeaders() }
        )
    }
}
  