import { Component, OnInit } from '@angular/core';
import { ElevatedResultsService } from 'src/app/service/elevated-results.service';

export class GlobalSearchItem{
  constructor(public productName:string, public store:string,public rating:string,public imageUrl:string,public url:string,public price:string){

  }
}

@Component({
  selector: 'app-elevated-search',
  templateUrl: './elevated-search.component.html',
  styleUrls: ['./elevated-search.component.css']
})
export class ElevatedSearchComponent implements OnInit {

  searchToken = 'iphone13'
  showSpinner = false;
  isErroroccred = false
  errorMessage='An Error Occured While fetching data from following sites...'

  amazonSearchitems = [
    new GlobalSearchItem('','','','','','')
  ]

  krogerSearchitems = [
    new GlobalSearchItem('','','','','','')
  ]

  targetSearchitems = [
    new GlobalSearchItem('','','','','','')
  ]

  costcoSearchitems = [
    new GlobalSearchItem('','','','','','')
  ]

  constructor(
    private elevatedSearchService:ElevatedResultsService
  ) { }

  ngOnInit(): void {
    this.fetchResults()
  }

  fetchResults(){
    this.amazonSearchitems = [];
    this.krogerSearchitems = [];
    this.targetSearchitems = [];
    this.costcoSearchitems = [];
    this.showSpinner = true;
    this.isErroroccred = false
    this.errorMessage = 'An Error Occured While fetching data from following sites...'
    this.fetchAmazonSearchResults(this.searchToken);
    this.fetchCostcoSearchResults(this.searchToken);
    this.fetchTargetSearchResults(this.searchToken);
    this.fetchKrogerSearchResults(this.searchToken);
  }

  fetchAmazonSearchResults(searchToken:string){
    this.elevatedSearchService.getAmazonSearchResult(this.searchToken).subscribe(
      response => {
        this.amazonSearchitems = response
        this.showSpinner = false
      },
      error =>{
        this.showSpinner = false
        this.showSpinner = false
        this.isErroroccred = true
        this.errorMessage +=' Amazon'
      }
    )
    
  }

  fetchKrogerSearchResults(searchToken:string){
    this.elevatedSearchService.getKrogerSearchResult(this.searchToken).subscribe(
      response => {
        this.krogerSearchitems = response
        this.showSpinner = false
      },
      error =>{
        this.showSpinner = false
        this.isErroroccred = true
        this.errorMessage +=' Kroger'
      }
    )
  }


  fetchTargetSearchResults(searchToken:string){
    this.elevatedSearchService.getTargetSearchResult(this.searchToken).subscribe(
      response => {
        this.targetSearchitems = response
        this.showSpinner = false
      },
      error =>{
        this.showSpinner = false
        this.isErroroccred = true
        this.errorMessage +=' Target'
      }
    )
    
  }

  fetchCostcoSearchResults(searchToken:string){
    this.elevatedSearchService.getCostcoSearchResult(this.searchToken).subscribe(
      response => {
        this.costcoSearchitems = response
        this.showSpinner = false
      },
      error =>{
        this.showSpinner = false
        this.showSpinner = false
        this.isErroroccred = true
        this.errorMessage +=' Costco'
      }
    )
    
  }
}
