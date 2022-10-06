import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';

export class feedback{
  constructor(public username:string,public email:string,public feedback:string,public feedbackDate:Date){

  }
}

@Component({
  selector: 'app-contactus',
  templateUrl: './contactus.component.html',
  styleUrls: ['./contactus.component.css']
})
export class ContactusComponent implements OnInit {

  fbk = new feedback('','','',new Date())

  errorOccured = false;

  success = false

  constructor(
    private adminService:AdminService
  ) { }

  ngOnInit(): void {
  }

  savefeedback(){
    this.errorOccured = false
    this.success = false
    this.adminService.savefeedback(this.fbk).subscribe(
      response => ( this.success = true, this.fbk =  new feedback('','','',new Date())),
      error => (this.errorOccured = true)
    )
  }


}
