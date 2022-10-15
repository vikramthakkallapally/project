import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserAuthService } from './user-auth.service';
import { environment } from 'src/environments/environment';
import { OtpAuthRequest, UpdatePasswordRequest } from '../common/reset-password/reset-password.component';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  PATH_OF_API = environment.baseUri

  ADMIN_ROLE = environment.adminRole

  USER_ROLE = environment.userRole

  requestHeader = new HttpHeaders(
    {
      "NO-AUTH": "True"
    }
  )

  constructor(
    private httpClient: HttpClient,
    private userAuth: UserAuthService
  ) { }

  public login(loginData: NgForm) {

    return this.httpClient.post(this.PATH_OF_API + '/authenticate', loginData, { headers: this.requestHeader })

  }

  public register(userData: NgForm) {

    return this.httpClient.post(this.PATH_OF_API + '/user/registerNewUser', userData, { headers: this.requestHeader })

  }

  public isAdmin():boolean{
   return this.roleMatch([this.ADMIN_ROLE])
  }

  public isStandardUser():boolean{
    return this.roleMatch([this.USER_ROLE])
   }


  public roleMatch(allowedRoles: any): boolean {
    let isMatch = false
    const roles: any = this.userAuth.getRoles()

    if (roles != null && roles) {
      for (let i = 0; i < roles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (roles[i].roleName === allowedRoles[j]) {
            isMatch = true;
            return true;
          }
        }
      }
    }

    return isMatch;

  }


  public sendOtp(email:string){
    return this.httpClient.get(this.PATH_OF_API + `/reset/sendotp?email=${email}`,{ headers: this.requestHeader })
  }

  public validateOtp(otpAuthRequest:OtpAuthRequest){
    return this.httpClient.post(this.PATH_OF_API + `/reset/verifyOtp`,otpAuthRequest,{ headers: this.requestHeader })
  }

  public updatePassword(updtReq:UpdatePasswordRequest){
    return this.httpClient.post(this.PATH_OF_API + `/reset/password`,updtReq,{ headers: this.requestHeader })
  }

}
