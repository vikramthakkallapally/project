package com.price.comparision.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.price.comparision.ecom.model.Message;
import com.price.comparision.ecom.repository.MessageRepo;

@RestController
public class MessageController {
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    private MessageRepo messageRepo;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message) {
        messageRepo.save(message);
        simpMessagingTemplate.convertAndSend( "/topic/"+to, message);
    }
    
}
