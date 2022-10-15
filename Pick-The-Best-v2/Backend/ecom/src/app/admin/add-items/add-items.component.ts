import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';


export class Item{
  constructor(public description:string, public amazonId:string, public costcoId:string, public krogerId:string, public targetId:String){
  }
}

export class Item2{
  constructor(public id:number,public description:string, public amazonId:string, public costcoId:string, public krogerId:string, public targetId:String){
  }
}

@Component({
  selector: 'app-add-items',
  templateUrl: './add-items.component.html',
  styleUrls: ['./add-items.component.css']
})
export class AddItemsComponent implements OnInit {

  tmpItem = new Item('','','','','')

  items: Array<Item> = []

  isValidFormFlag = true

  isSuccessRequest = false

  savedObjects = 0

  constructor(
    private adminService:AdminService
  ) { }

  ngOnInit(): void {
  }

  addItem(){

    if(this.isValidForm()){

      this.isValidFormFlag = true
      this.isSuccessRequest =false
      
      if(this.tmpItem.amazonId.trim().length == 0)
         this.tmpItem.amazonId = 'N/A'
      if(this.tmpItem.targetId.trim().length == 0)
        this.tmpItem.targetId = 'N/A'
      if(this.tmpItem.costcoId.trim().length == 0)
        this.tmpItem.costcoId = 'N/A'
      if(this.tmpItem.krogerId.trim().length == 0)
        this.tmpItem.krogerId = 'N/A'
      if(this.tmpItem.description.trim().length == 0)
        this.tmpItem.description = 'Not Available'

      this.items.push(new Item(this.tmpItem.description,this.tmpItem.amazonId, this.tmpItem.krogerId,this.tmpItem.costcoId,this.tmpItem.targetId))
      this.tmpItem.description=''
      this.tmpItem.amazonId=''
      this.tmpItem.krogerId=''
      this.tmpItem.costcoId=''
      this.tmpItem.targetId=''

    }else{
      this.isValidFormFlag = false
    }
  }

  deleteItem(i:number){
    this.items.splice(i,1);
  }

  saveItems(){
    this.adminService.saveItems(this.items).subscribe(
      response =>{
        this.isSuccessRequest = true
        this.items = []
        this.savedObjects = response
      },
      error =>{
        this.isSuccessRequest = false
      }
    )
  }

  isValidForm():boolean{
    let flag = false
    if(this.tmpItem.amazonId.trim().length > 0)
      flag = true
    if(this.tmpItem.targetId.trim().length > 0)
      flag = true
    if(this.tmpItem.costcoId.trim().length > 0)
      flag = true
    if(this.tmpItem.krogerId.trim().length > 0)
      flag = true
    return flag
  }
}
