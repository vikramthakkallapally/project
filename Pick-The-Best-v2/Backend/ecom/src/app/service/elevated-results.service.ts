import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { GlobalSearchItem } from '../search/elevated-search/elevated-search.component';

@Injectable({
  providedIn: 'root'
})
export class ElevatedResultsService {

  globalSearchUri = environment.globalSearchUri

  constructor(
    private httpClient:HttpClient
    ) { }

  getAmazonSearchResult(searchToken:string){
    return this.httpClient.get<GlobalSearchItem[]>(`${this.globalSearchUri}/amazon/getProductsInfo?searchToken=${searchToken}`)
  }

  getKrogerSearchResult(searchToken:string){
   return this.httpClient.get<GlobalSearchItem[]>(`${this.globalSearchUri}/kroger/getProductsInfo?searchToken=${searchToken}`)
  }

  getTargetSearchResult(searchToken:string){
    return this.httpClient.get<GlobalSearchItem[]>(`${this.globalSearchUri}/target/getProductsInfo?searchToken=${searchToken}`)
  }
  getCostcoSearchResult(searchToken:string){
    return this.httpClient.get<GlobalSearchItem[]>(`${this.globalSearchUri}/costco/getProductsInfo?searchToken=${searchToken}`)
  }

}



