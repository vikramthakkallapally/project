import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable } from 'rxjs';
import { UserService } from '../service/user-service.service';
import { UserAuthService } from '../service/user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor( 
    private userAuthService:UserAuthService,
    private router:Router,
    private userService:UserService
    ){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {


      if(this.userAuthService.getToken() !== null )
      {
        const roles = route.data["roles"] as Array<string>
        if(roles){
            const match = this.userService.roleMatch(roles);

            if(match){
              return true;
            }else{
              this.router.navigate(['/forbidden'])
              return false
            }

        }

      }

      this.router.navigate(['/login'])
      return false
  }
  
}
