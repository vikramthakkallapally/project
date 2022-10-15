import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRoles(roles:[]){
    localStorage.setItem("roles",JSON.stringify(roles))
  }

  public getUserName(){
    return localStorage.getItem("username") as string;
  }

  public setUserName(username:string){
    return localStorage.setItem("username",username);
  }

  public getRoles():[]{
    return JSON.parse(localStorage.getItem('roles') as string);
  }

  public setToken(jwtToken:string){
    localStorage.setItem("jwtToken",jwtToken)
  }

  public getToken():string{
    return localStorage.getItem("jwtToken") as string
  }

  public clear(){
    localStorage.clear()
  }

  public isLoggedIn(){
    return this.getRoles() && this.getToken()
  }

}
