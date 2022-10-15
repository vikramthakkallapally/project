import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/service/user-auth.service';
import { UserService } from 'src/app/service/user-service.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  appName:string= ""

  constructor(
    private userAuthService:UserAuthService,
    private router:Router,
    private userService:UserService
  ) { }

  ngOnInit(): void {
    this.appName = environment.appName;
  }

  public isLoggedIn(){
    return this.userAuthService.isLoggedIn()
  }

  public logout(){
    this.userAuthService.clear()
    this.router.navigate(['/home'])
  }

  public adminUserMatch(){
    return this.userService.roleMatch(['Admin'])
  }

  public userUserMatch(){
    return this.userService.roleMatch(['User'])
  }

  public isAdminUser(){
    return this.userService.isAdmin()
  }

  public isStandardUser(){
    return this.userService.isStandardUser()
  }

  public enableNavigation(){
   
    let elements: HTMLCollectionOf<HTMLUListElement> = document.getElementsByTagName("ul")

    console.log(elements)
    for(var i = 0; i< elements.length; i++){
      elements[i].removeAttribute('display')
    }
  }

  public disable(){
    let elements: HTMLCollectionOf<HTMLUListElement> = document.getElementsByTagName("ul")

    for(var i = 0; i< elements.length; i++){
      elements[i].classList.add('hide')
    }
  }

}
