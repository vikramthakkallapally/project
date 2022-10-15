import { Component, OnInit } from '@angular/core';
import { feedback } from 'src/app/request/contactus/contactus.component';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-view-feebacks',
  templateUrl: './view-feebacks.component.html',
  styleUrls: ['./view-feebacks.component.css']
})
export class ViewFeebacksComponent implements OnInit {

  startDate = new Date(new Date().getTime() - 24*60*60*1000)
  endDate = new Date()
  errorOccured = false
  errorMessage =''
  feedbacks:Array<feedback> = []

  constructor(
    private adminService:AdminService
  ) { }

  ngOnInit(): void {
  }

  fetchFeedbacks(){
    this.adminService.fetchFeedBacks(new Date(this.startDate),new Date(this.endDate)).subscribe(
      Response => {this.feedbacks = Response},
      errorv => {this.errorOccured = true,this.errorMessage = 'Some error occured while fetching data'}
    )
  }

}
