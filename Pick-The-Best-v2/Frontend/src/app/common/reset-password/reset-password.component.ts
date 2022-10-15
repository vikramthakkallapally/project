import { Component, OnInit } from '@angular/core';
import { EmailValidator, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { user } from 'src/app/common/register/register.component';
import { UserService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  constructor( private userService:UserService,private router:Router) { }

  ngOnInit(
   
  ): void {

  }

  isOtpSent = false

  isTokenVerified = false

  errorOccured = true

  token = ''

  message = ''

  password1 = ''

  password2 = ''

  user = new user('', '', '', '','','') 

  setPassword(){
    this.errorOccured = false
    if(this.password1 === this.password2){
      let updatePasswordRequest = new UpdatePasswordRequest(this.user.email, this.token, this.password1)

      this.userService.updatePassword(updatePasswordRequest).subscribe(
        response => {
          this.router.navigate(['/login'])
        },
        error =>{
          this.errorOccured = false
          this.message = 'Something went wrong'
        }
      )

    }else{
      this.errorOccured = false
      this.message ="Password not matched. Try Again"
    }


  }

  validateToken(){

    this.errorOccured = false

    if(this.token.length !== 6){
      this.errorOccured = true
      this.message = 'OTP must be 6 character'
    }else{
      let oareq = new OtpAuthRequest(this.user.email, this.token);

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

    this.userService.sendOtp(this.user.email).subscribe(
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

export class OtpAuthRequest{
  constructor(public email:string, public token:string){

  }
}

export class UpdatePasswordRequest{
  constructor(public email:string, public token:string,public password:String){

  }
}

