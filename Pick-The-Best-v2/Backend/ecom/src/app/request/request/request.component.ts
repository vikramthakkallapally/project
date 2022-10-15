import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';


export class request{
  constructor(public username:string ,public productname:string, public productDescription:String, public email:string){

  }
}

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {

  request = new request('','','','')

  requestId = 0

  errorOccured = false;

  success = false

  constructor(
    private adminService:AdminService
  ) { }

  ngOnInit(): void {
  }

  saveRequest(){
    this.errorOccured = false
    this.success = false
    this.adminService.saveRequest(this.request).subscribe(
      response => (this.requestId = response, this.success = true),
      error => (this.errorOccured = true)
    )
  }

}
