import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/service/user-auth.service';
import { UserService } from 'src/app/service/user-service.service';
import { OtpAuthRequest } from '../reset-password/reset-password.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    private userService:UserService,
    private userAuthService:UserAuthService,
    private router:Router
  ) { }

  newuser = new user('', '', '', '','','')

  isOtpSent = false

  isTokenVerified = false

  errorOccured = true

  otpToken = ''

  message = ''

  password1 = ''

  ngOnInit(): void {
  }

  register(registerForm:NgForm){
    this.errorOccured = false
    this.userService.register(registerForm.value).subscribe(
      (response:any) => {
        this.errorOccured = true
          this.router.navigate(['/user'])
      },
      (error) =>{
        this.message = "Something went wrong"
      }
    )
  }


  validateToken(){

    this.errorOccured = false

    if(this.otpToken.length !== 6){
      this.errorOccured = true
      this.message = 'OTP must be 6 character'
    }else{
      let oareq = new OtpAuthRequest(this.newuser.email, this.otpToken);

      this.userService.validateOtp(oareq).subscribe(
        response =>{
          this.isTokenVerified = true
        },
        error =>{
          this.errorOccured = true
          this.message = 'Invalid OTP'
        }
      )

    }
  }

  public sendOtp(){
    this.errorOccured = false
    this.userService.sendOtp(this.newuser.email).subscribe(
     response =>{
        this.isOtpSent = true
     },
     error =>{
        console.log(error)
        this.errorOccured = true
        this.message = 'Something went wrong'
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
