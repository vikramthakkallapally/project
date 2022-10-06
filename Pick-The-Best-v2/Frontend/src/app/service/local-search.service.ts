import { Injectable } from '@angular/core';
import { searchResult } from '../search/search/search.component';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LocalSearchService {

  localSearchUri = environment.localSearchUri

  constructor(
    private httpClient:HttpClient,
  ) { }

  getSearchItems(searchToken:string){
    return this.httpClient.get<searchResult[]>(`${this.localSearchUri}/getProductsInfo?searchToken=${searchToken}`)
  }

}
