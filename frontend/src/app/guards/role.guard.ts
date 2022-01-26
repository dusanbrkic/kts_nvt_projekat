import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(public authService: AuthService, public router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles: string = route.data.expectedRoles;
    const tokenString = localStorage.getItem("user");

    if (!tokenString) {
      this.router.navigate(["/"]);
      return false;
    }

    const token=JSON.parse(tokenString)

    if (route.data.role!==token.role) {
      this.router.navigate(["/"]);
      return false;
    }
    return true;
  }

  
}
