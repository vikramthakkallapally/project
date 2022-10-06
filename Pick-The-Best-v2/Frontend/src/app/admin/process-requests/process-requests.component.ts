import { StringMapWithRename } from '@angular/compiler/src/compiler_facade_interface';
import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';
import { UserAuthService } from 'src/app/service/user-auth.service';
import { UserService } from 'src/app/service/user-service.service';

export class request2{
  constructor(public id:number,public username:string ,public productname:string, public productDescription:String, public email:string,public requestStatus:string,public closedBy:string,public requestDate:Date){

  }
}


@Component({
  selector: 'app-process-requests',
  templateUrl: './process-requests.component.html',
  styleUrls: ['./process-requests.component.css']
})
export class ProcessRequestsComponent implements OnInit {

  public isCollapsed = [];
  errorOccured = false
  errorMessage =''
  isUpdated = false
  successMessage =''
  userRequests:Array<request2> = []

  startDate = new Date(new Date().getTime() - 24*60*60*1000)
  endDate = new Date()

  constructor(
    private adminService:AdminService,
    private userAuthService:UserAuthService
  ) { }

  ngOnInit(): void {
  } 

  fetchRequests(){ 
    this.adminService.fetchRequests(new Date(this.startDate),new Date(this.endDate)).subscribe(
        response =>{
          this.errorOccured = false
          this.userRequests = response
        },err=>{
          this.errorOccured = true;
          this.errorMessage = err.getTime + 'Error occured whilr Fetching Data'
        }
    )
  }

  closeRequest(id :number){

    for(var i = 0; i< this.userRequests.length; i++){
      this.isUpdated = false
      this.errorOccured = false
      if(this.userRequests[i].id === id){
        this.userRequests[i].requestStatus = 'Y'
        this.userRequests[i].closedBy = this.userAuthService.getUserName()
        
        this.adminService.saveRequest(this.userRequests[i]).subscribe(
          res =>{
            this.isUpdated = true
            this.successMessage = 'The Request '+id+' closed successfully'
            this.fetchRequests()
          },
          err =>{
            this.errorOccured = true
            this.errorMessage = 'Some error occured while closing the Request ' +id 
          }
        )
        break;
      }
    }

  }

}
