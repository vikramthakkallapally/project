import { Component, OnInit } from '@angular/core';
import { Form, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user-service.service';
import { UserAuthService } from 'src/app/service/user-auth.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private UserService:UserService,
    private userAuthService:UserAuthService,
    private router:Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
  }

  login(loginForm:NgForm){
    this.UserService.login(loginForm.value).subscribe(
      (response:any) => {
        this.userAuthService.setToken(response.jwtToken)
        this.userAuthService.setRoles(response.user.roles)
        this.userAuthService.setUserName(response.user.userName)
        const role = response.user.roles[0].roleName

        if(this.UserService.isAdmin()){
          this.router.navigate(['admin/viewRequests'])
        }else if(this.UserService.isStandardUser()){
          this.router.navigate(['user/search'])
        }else{
          this.router.navigate(['user/search'])
        }
      },
      (error) =>{
        if(error.status = 401){
          this.toastr.error("Invalid Username or Password", "");
        }
      }
    )
  }
  }


