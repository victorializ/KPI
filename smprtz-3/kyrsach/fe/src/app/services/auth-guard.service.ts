import { Injectable } from "@angular/core";
import { Router, CanActivate } from "@angular/router";

import { BackendService } from "./backend.service";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private _router: Router, private _be: BackendService) { }

  canActivate() {
    if (this._be.isLoggedIn()) {
      return true;
    } else {
      this._router.navigate(["login"]);
      return false;
    }
  }
}

@Injectable()
export class AdminAuthGuard implements CanActivate {
  constructor(private _router: Router, private _be: BackendService) { }

  canActivate() {
    if (this._be.isLoggedIn() && this._be.isAdmin()) {
      return true;
    } else {
      this._router.navigate(["login"]);
      return false;
    }
  }
}
