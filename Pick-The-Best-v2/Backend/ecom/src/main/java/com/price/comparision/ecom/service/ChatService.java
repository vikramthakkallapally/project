package com.price.comparision.ecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.price.comparision.ecom.model.ChatMap;
import com.price.comparision.ecom.model.Role;
import com.price.comparision.ecom.model.User;
import com.price.comparision.ecom.repository.ChatMapRepo;
import com.price.comparision.ecom.repository.UserDao;
import com.price.comparision.ecom.util.CommonUtil;


@Transactional
@Service
public class ChatService {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private CommonUtil commonUtil;
    
    @Autowired
    private ChatMapRepo chatMapRepo;
    
    
    public List<String> initiateAdmin(String username){
        
        try {
            
            User user = userDao.findById(username).get();
            Set<Role> roles = user.getRoles();
            
            boolean isAdmin = false;
            
            for(Role r: roles) {
                if(r.getRoleName().equalsIgnoreCase("ADMIN"))
                    isAdmin = true;
            }
            
            if(isAdmin) {
                
                List<String> chatList = new ArrayList<String>();
                
                List<ChatMap> adminExist = chatMapRepo.checkAdminExist(username);
                
                if(adminExist.size() > 0) {
                    chatList.add(adminExist.get(0).getTmpAdminId());
                    chatList.add(adminExist.get(0).getTmpUserId());
                    chatList.add(String.valueOf(adminExist.get(0).getChatId()));
                }else {
                    ChatMap c = new ChatMap();
                    c.setAdminname(user.getUserName());
                    c.setStatus(0);
                    c.setTmpAdminId(commonUtil.getUuid());
                    c.setTmpUserId(commonUtil.getUuid());
                    chatList.add(c.getTmpAdminId());
                    chatList.add(c.getTmpUserId());
                    chatMapRepo.save(c);
                    chatList.add(String.valueOf(c.getChatId()));
                    
                }  
                
                return chatList;
                
            }else {
                throw new RuntimeException("user is not admin");
            } 
        }catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("user not found");
        }
    }
    
    public List<String> initiateUser(String username){
        try {
            
            User user = userDao.findById(username).get();
            List<String> chatList = new ArrayList<String>();
            
            List<ChatMap> userExist = chatMapRepo.checkUserExist(username);
            
            if(!userExist.isEmpty()) {
                chatList.add(userExist.get(0).getTmpUserId());
                chatList.add(userExist.get(0).getTmpAdminId());  
                chatList.add(String.valueOf(userExist.get(0).getChatId()));
            }else {
                List<ChatMap> chatMap = chatMapRepo.findAvailableAdmin();
                
                if(!chatMap.isEmpty()) {
                   ChatMap c = chatMap.get(0);
                   c.setUsername(user.getUserName());
                   chatMapRepo.save(c);
                   
                   chatList.add(chatMap.get(0).getTmpUserId());
                   chatList.add(chatMap.get(0).getTmpAdminId()); 
                   chatList.add(String.valueOf(c.getChatId()));
                   
                }else {
                    throw new RuntimeException("available admin not found");
                }
            }
            
            return chatList;

        }catch(Exception ex) {
            throw new RuntimeException("user not found");
        }
    }
    
    
    public boolean finishChat(String tmpUserId) {
        List<ChatMap> chatMap = chatMapRepo.findChat(tmpUserId);
        ChatMap c =  chatMap.get(0);
        c.setStatus(1);
        chatMapRepo.save(c);
        
        return true;
    }
    
    public boolean ischatActive(Long chatId) {
        try{
            List<ChatMap> isActive = chatMapRepo.isChatActive(chatId);
            if(isActive.size() > 0){
               return true;
            }else {
                return false;
            }
            
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    
}
