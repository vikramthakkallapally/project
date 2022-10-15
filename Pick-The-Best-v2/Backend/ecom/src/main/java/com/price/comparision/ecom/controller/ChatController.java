package com.price.comparision.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.price.comparision.ecom.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private ChatService chatService;
    
    
   @GetMapping("/registerAdminForChat")
   public List<String> registerAdmin(@RequestParam(name = "username") String username){
       return chatService.initiateAdmin(username);
   }
   
   @GetMapping("/registerUserForChat")
   public List<String> registerUser(@RequestParam(name = "username") String username){
       return chatService.initiateUser(username);
   }
   
   @GetMapping("/finishChat")
   public ResponseEntity<Void> finishChat(@RequestParam(name = "chatId") String tmpChatId) {
       chatService.finishChat(tmpChatId);
       return ResponseEntity.noContent().build();
   } 
   
   @GetMapping("/isChatActive")
   public ResponseEntity<Void> isChatActive(@RequestParam(name = "chatId") Long chatId) {
       if(chatService.ischatActive(chatId))
           return ResponseEntity.noContent().build();
       else
           throw new RuntimeException("chat is in Active");
   } 

}
