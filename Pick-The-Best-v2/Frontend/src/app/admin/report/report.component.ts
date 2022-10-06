import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';

export class reportItem{
  constructor(public searchToken:string, public count:number){
  }
}

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  errorOccured = false
  errorMessage =''

  reportItems:Array<reportItem> = []

  startDate = new Date(new Date().getTime() - 24*60*60*1000)
  endDate = new Date()

  constructor(
    private adminService:AdminService
  ) { }

  ngOnInit(): void {
    this.fetchreport()
  }


  fetchreport(){
    this.errorOccured = false
    this.adminService.fetchReports(new Date(this.startDate),new Date(this.endDate)).subscribe(
      Response => {
        this.reportItems = Response
        if(this.reportItems.length == 0){
          this.errorMessage = 'No Items found for given criteria'
          this.errorOccured = true}
      },error => {
        this.errorOccured = true
        this.errorMessage = 'Some error occured while fetching the data'
      }

    )
  }
}
