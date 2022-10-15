import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/service/user-auth.service';
import { UserService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    private UserService:UserService,
    private userAuthService:UserAuthService,
    private router:Router
  ) { }

  newuser = new user('', '', '', '','','')

  ngOnInit(): void {
  }

  register(registerForm:NgForm){
    this.UserService.register(registerForm.value).subscribe(
      (response:any) => {
          this.router.navigate(['/user'])
      },
      (error) =>{
        console.log(error)
      }
    )
  }
}

export class user{
  constructor(  
    public userName:string, 
    public userFirstName:string, 
    public userLastName:string, 
    public email:string, 
    public publicuserPassword:string, 
    public confirmPassword:string ){
  }
}
