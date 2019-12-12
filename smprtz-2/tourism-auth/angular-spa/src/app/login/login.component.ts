import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public email: string = '';
  public password: string = '';
  public reenter: string = '';
  public name: string = '';
  public registration: boolean = false;

  constructor(private _be: BackendService, private _router: Router) { }

  ngOnInit() {

  }

  isLoggedIn(): boolean {
    return this._be.isLoggedIn();
  }

  logout() {
    return this._be.logout();
  }

  login() {
    if(this.email && this.password) {
      this._be.login(this.email, this.password)
      .subscribe(
        () => alert("Success!"), 
        () => alert("Failture!")
      );
      this._router.navigate(["order"]);
    } else {
      alert("Not all fields are filled!");
    }
  }

  register() {
    if(this.name && this.email && this.password && this.reenter && this.registration) {
      if(this.reenter === this.password) {
        this._be.register(this.name, this.email, this.password).subscribe(
          () => alert("Success!"), 
          () => alert("Failture!")
        );
      } else {
        alert("Passwords not match!");
      }
    } else if(this.registration) {
      alert("Not all fields are filled!");
    } else {
      this.registration = !this.registration;
    }
  }
}
