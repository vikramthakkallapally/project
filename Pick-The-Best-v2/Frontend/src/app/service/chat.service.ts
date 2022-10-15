import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(
    private httpClient:HttpClient
    ) { }

    baseUri = environment.baseUri

  getAdminChatId(username:string){
    return this.httpClient.get<string[]>(`${this.baseUri}/chat/registerAdminForChat?username=${username}`)
  }

  getUserChatId(username:string){
    return this.httpClient.get<string[]>(`${this.baseUri}/chat/registerUserForChat?username=${username}`)
  }

  finishChat(tmpUserId:string){
    return this.httpClient.get(`${this.baseUri}/chat/finishChat?chatId=${tmpUserId}`)
  }

  isActive(chatId:string){
    return this.httpClient.get(`${this.baseUri}/chat/isChatActive?chatId=${chatId}`)
  }
}
