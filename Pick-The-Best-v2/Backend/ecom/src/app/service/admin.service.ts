import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Item, Item2 } from '../admin/add-items/add-items.component';
import { request2 } from '../admin/process-requests/process-requests.component';
import { reportItem } from '../admin/report/report.component';
import { ContactusComponent, feedback } from '../request/contactus/contactus.component';
import { request } from '../request/request/request.component';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(
    private httpClient: HttpClient
  ) { }


  adminSearchUrl = environment.adminSearchuri
  statsUrl = environment.statsUri
  username = 'admin'


  saveRequest(req: request) {
    return this.httpClient.post<number>(`${this.adminSearchUrl}/saveRequest`, req);
  }

  savefeedback(fbk: feedback) {
    return this.httpClient.post<number>(`${this.adminSearchUrl}/contactus`, fbk);
  }

  saveItems(items: any) {
    return this.httpClient.post<number>(`${this.adminSearchUrl}/addItems?username=${this.username}`, items)
  }

  fetchItems(searchToken:String){
    return this.httpClient.get<Item2[]>(`${this.adminSearchUrl}/getItems?searchToken=${searchToken}`)
  }

  deleteItem(id:number){
    return this.httpClient.get<Number>(`${this.adminSearchUrl}/deleteItem?id=${id}`)
  }
  

  fetchReports(startDate:Date,endDate:Date) {
    return this.httpClient.get<reportItem[]>(`${this.statsUrl}/getStats?startDate=${startDate.getTime()}&endDate=${endDate.getTime()}`)
  }
  
  fetchRequests(startDate:Date,endDate:Date){
    return this.httpClient.get<request2[]>(`${this.adminSearchUrl}/getRequests?startDate=${startDate.getTime()}&endDate=${endDate.getTime()}`)
  }

  fetchFeedBacks(startDate:Date,endDate:Date){
    return this.httpClient.get<feedback[]>(`${this.adminSearchUrl}/getFeedbacks?startDate=${startDate.getTime()}&endDate=${endDate.getTime()}`)
  }

}
