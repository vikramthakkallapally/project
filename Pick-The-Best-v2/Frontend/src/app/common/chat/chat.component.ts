import { Component, OnInit } from '@angular/core';
import { SelectMultipleControlValueAccessor } from '@angular/forms';
import { ChatService } from 'src/app/service/chat.service';
import { UserAuthService } from 'src/app/service/user-auth.service';
import { UserService } from 'src/app/service/user-service.service';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  

  constructor(
    private chatService:ChatService,
    private userAuthService:UserAuthService,
    private userService:UserService,
    private router:Router
  ) { }

   username = this.userAuthService.getUserName()

   sender=''

   client=''

   chatId=''

   isUserFoundForAdmin = false

   errorOccured = false

   isAdmin = false

   error  = ''

   message = new Message('',this.sender,this.client,this.chatId)

   private stompClient:any = null;

   messages: Array<Message> = [];

   isChatOpen = false

   noAdminFound = false
   

  ngOnInit(): void {
   
    this.username = this.userAuthService.getUserName()

    this.isAdmin = this.userService.isAdmin()

    if(this.isAdmin){
      this.getAdminChatId()
    }else{
      this.getUserChatId()
    }
  }


  getAdminChatId(){

    this.errorOccured = false

    this.chatService.getAdminChatId(this.username).subscribe(
      response =>{
        this.sender = response[0]
        this.client = response[1]
        this.chatId = response[2]
        this.connectToMessageServer() 
      }, 

      error => {
        this.errorOccured = true
        this.error = error
      }
    )
  }

  getUserChatId(){

    this.errorOccured = false

    this.chatService.getUserChatId(this.username).subscribe(
      response =>{
        this.sender = response[0]
        this.client = response[1]
        this.chatId = response[2]
        this.connectToMessageServer() 
        this.noAdminFound = false
        this.isChatOpen = true
        console.log('success')
      }, 

      error => {
        this.errorOccured = true
        this.error = error
        this.noAdminFound = true
        this.isChatOpen = false
      }
    )
  }


  finishChat(){

    this.errorOccured = false

    const  chatId =  this.isAdmin ? this.client : this.sender
    this.chatService.finishChat(chatId).subscribe(
      response => {
        if(this.isAdmin){
          this.getAdminChatId();
        }

        if(!this.isAdmin){
          this.router.navigate(['/search'])
        }
        this.isChatOpen = false
        this.messages = []

      },

      error =>{
        this.errorOccured = true
        this.error = error
      }
    
    )

  }

  closeChat(){

    this.errorOccured = false

    const  chatId =  this.isAdmin ? this.client : this.sender
    this.chatService.finishChat(chatId).subscribe(
      response => {
        this.router.navigate(['/search'])
      },

      error =>{
        this.errorOccured = true
        this.error = error
      }
    
    )

  }

  isChatActive(){
    this.chatService.isActive(this.chatId).subscribe(
      response =>{
        this.isChatOpen = true

      },
      error =>{
        this.isChatOpen = false
        this.messages = []
        if(this.isAdmin)
          this.getAdminChatId()
        else
          this.getUserChatId()
      }
    )
  }

  connectToMessageServer() {
    const token = this.userAuthService.getToken();

    const socket = new SockJS(environment.baseUri+'/chat',{'Authorization':`Bearer ${token}`,'Access-Control-Allow-Origin': 'true'});
    this.stompClient = Stomp.over(socket);
    this.stompClient.debug = () => {}
    var headers = {
      Authorization : 'Bearer ' + token,
    };
   

    this.stompClient.connect(headers,  (frame:any) => {
      
      this.stompClient.subscribe('/topic/'+this.sender, (response: any) => {
        let inboundMessage = JSON.parse(response.body)
        this.messages.push(new Message(inboundMessage.message, inboundMessage.sender,inboundMessage.client,this.chatId))
      })

    });
  }

  sendMessage(){
    this.isChatActive()
    if(this.message.message.trim().length > 0){
      const token = this.userAuthService.getToken();
      this.stompClient.send(
        '/app/chat/'+this.client, {'Authorization':`Bearer ${token}`,'Access-Control-Allow-Origin': 'true'},
        JSON.stringify({
            message: this.message.message,
            sender: this.sender,
            client: this.client,
            chatId:this.chatId
      }))
      ;

      this.messages.push(new Message(this.message.message,this.sender,this.client,this.chatId))
      this.message.message = ''
    }else{
      this.message.message.trim
    }
  }

}

export class Message{
  constructor(public message:string,public sender:string,public client:string,public chatId:string){

  }
}
