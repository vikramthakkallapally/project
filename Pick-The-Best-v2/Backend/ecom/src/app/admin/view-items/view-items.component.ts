import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';
import { Item, Item2 } from '../add-items/add-items.component';

@Component({
  selector: 'app-view-items',
  templateUrl: './view-items.component.html',
  styleUrls: ['./view-items.component.css']
})
export class ViewItemsComponent implements OnInit {

  public searchToken = ''

  isErrorOccured = false
  isUpdated = false
  errorMessage = 'Some error occured'
  successMessage = ''

  items:Array<Item2> =[]

  constructor(
    private adminService:AdminService
  ) { }

  ngOnInit(): void {
  }

  fetchItems(){
    this.isErrorOccured = false
    this.adminService.fetchItems(this.searchToken).subscribe(
      response => {
        this.items = response
      },
      error=>{
        this.isErrorOccured = true
        this.errorMessage = 'Some error occured while fetching the details' 
      }
    )
  }

  deleteItem(id:number){
    this.isErrorOccured = false
    this.isUpdated = false

    this.adminService.deleteItem(id).subscribe(
      response => {
        this.isUpdated = true
        this.fetchItems()
        this.successMessage='The Item having id ' + response + 'Deleted Successfully'
      },

      error =>{
        this.isErrorOccured = true
      }
    )

  }

  saveItem(id:number){

    let amz = <HTMLInputElement>document.getElementById('amazon'+id)
    let trg = <HTMLInputElement>document.getElementById('target'+id)
    let csc = <HTMLInputElement>document.getElementById('costco'+id)
    let kgr = <HTMLInputElement>document.getElementById('kroger'+id)
    let dsc = <HTMLInputElement>document.getElementById('description'+id)

    this.isErrorOccured = false
    this.isUpdated = false
    
    let item = new Item2(id,dsc.value,amz.value,csc.value,kgr.value,trg.value)

    this.adminService.saveItems([item]).subscribe(
      response => {
        this.isUpdated = true
        this.fetchItems()
        this.successMessage='The Record updated successfully'
      },

      error =>{
        this.isErrorOccured = true
      }
    )


  }

  editItem(id:number,action:boolean){
    console.log
    let amz = <HTMLInputElement>document.getElementById('amazon'+id)
    let trg = <HTMLInputElement>document.getElementById('target'+id)
    let csc = <HTMLInputElement>document.getElementById('costco'+id)
    let kgr = <HTMLInputElement>document.getElementById('kroger'+id)
    let dsc = <HTMLInputElement>document.getElementById('description'+id)
    amz.disabled = action
    trg.disabled = action
    csc.disabled = action
    kgr.disabled = action
    dsc.disabled = action
  }

  isenabled(id:number){
    let dsc = <HTMLInputElement>document.getElementById('description'+id)
    return (dsc.disabled == true)
  }


  

}
