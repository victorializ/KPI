import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map, tap, concatMap } from "rxjs/operators";
import { HttpClient } from "@angular/common/http";
import { Equipment, Auth, Roles, Feedback } from "../types";

@Injectable({
  providedIn: "root"
})
export class BackendService {
    static apiPath = "http://localhost:8080/";
    static eqPath = "equipment";
    static bookingPath = "booking";
    static userPath = "user";
    static find = "find";
    static delete = "delete";
    static update = "update";
    static login = "login";
    static register= "register";
    static feedback="feedback";

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
        const url = `${BackendService.apiPath}${BackendService.userPath}/${BackendService.login}`;
        return this._http.post(url, { email, password },
            { headers: this.getBasicHeaders() }
        ).pipe(
            tap((res: Auth) => this.auth = res)
        );
    }

    private getBasicHeaders() {
        const headers = {
          'Content-type': 'application/json',
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
        const url = `${BackendService.apiPath}${BackendService.userPath}/${BackendService.register}`;
        return this._http.post(url, {name, email, password},
            { headers: this.getBasicHeaders() }
        ).pipe(
            tap((res: Auth) => this.auth = res)
        );
    }


    public getEquipmentList() {
        const url = `${BackendService.apiPath}${BackendService.eqPath}`;
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

    public addEquipment(name: string, type: string, price: number, 
        rating: number, description: string) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}`;
        return this._http.post(url, {name, type, price, rating, description},
            { headers: this.getAuthHeaders() },
        )
    }
    
    public deleteEquipment(id: number) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${id}`;
        return this._http.delete(url,
          { headers: this.getAuthHeaders() }
        )
    }

    public updateEquipment(id: number, name: string, type: string, 
            price: number, rating: number, description: string) {
        const url = `${BackendService.apiPath}${BackendService.eqPath}/${id}`;

        return this._http.put(url, {name, type, price, rating, description},
            { headers: this.getAuthHeaders() }
          )
    }

    public addBooking(equipmentId) {
        const date = '05/07/2020 20:00:00';
        const url = `${BackendService.apiPath}${BackendService.bookingPath}`;
        return this._http.post(url, {touristId:  this.auth.user.id.toString(), equipmentId, date},
            { headers: this.getAuthHeaders() },
        )
    }

    public getBookingsList() {
        const url = (this.isLoggedIn() && this.isAdmin()) ? 
        `${BackendService.apiPath}${BackendService.bookingPath}` :
        `${BackendService.apiPath}${BackendService.bookingPath}/${BackendService.userPath}/${this.auth.user.id}`;
        
        return this._http.get(url,
            { headers: this.getAuthHeaders() }
        );
    }

    public getBooking(id) {
        const url = `${BackendService.apiPath}${BackendService.bookingPath}/${id}`;
        return this._http.get(url,
            { headers: this.getAuthHeaders() }
        );
    }

    public getFeedbacksByEquipmentList(id) {
        const url = `${BackendService.apiPath}${BackendService.feedback}`;
        return this._http.get(url,
            { headers: this.getBasicHeaders() }
        ).pipe(
            map((res: Array<Feedback>) => res.filter(f => {
                return Number(f.equipmentId) ===  Number(id);
            }))
        );
    }

    public postFeedback(bookingId, text, rating, equipmentId) {
        const date = '05/07/2020 20:00:00';
        const url = `${BackendService.apiPath}${BackendService.feedback}`;
        return this._http.post(url, {bookingId, text, rating, date, equipmentId},
            { headers: this.getAuthHeaders() }
        );
    }

}
  