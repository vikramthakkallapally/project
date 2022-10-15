import { Component, OnInit } from '@angular/core';
import { LocalSearchService } from 'src/app/service/local-search.service';

export class searchtItem{
  constructor(public storeName:string, public price:string, public rating:string,public url:string ){

  }
}

export class searchResult {
  constructor (public description:string, public itemDetails: searchtItem[]){

  }
}


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  itemDetails = [
    new searchtItem('','','','')
  ]

  searchResults = [
        new searchResult('',this.itemDetails)
  ]
  
  showSpinner = false

  searchToken ='Apple'

  isresultsFound  = true

  content = false
 
  constructor(
    private localSearchService:LocalSearchService
  ) { }

  ngOnInit(): void {
    this.fecthLocalSearchResults(this.searchToken)
  }

  fecthLocalSearchResults(searchToken:string){
    this.showSpinner = true
    this.isresultsFound =true
    this.localSearchService.getSearchItems(this.searchToken).subscribe(
      response => this.doSuccessResponse(response),
      error => this.doErrorResponse(error)
    )
  }

  doSuccessResponse(response:any){
      console.log(response)
      this.searchResults = response;
      if(this.searchResults.length == 0){
        this.isresultsFound = false
        this.content = false
      }else{
        this.content = true
      }
      this.showSpinner=false
      
  }


  doErrorResponse(error:any){
    this.isresultsFound = false
    this.showSpinner = false
    this.content = false
  }

}
