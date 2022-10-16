package com.price.comparision.ecom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="chat_map")
public class ChatMap {
    
    @Id
    @GeneratedValue
    private Long chatId;
    
    private String username;
    
    private String adminname;
    
    private String tmpUserId;
    
    private String tmpAdminId;
    
    private int status;

    public ChatMap() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ChatMap(Long chatId, String username, String adminname, String tmpUserId, String tmpAdminId, int status) {
        super();
        this.chatId = chatId;
        this.username = username;
        this.adminname = adminname;
        this.tmpUserId = tmpUserId;
        this.tmpAdminId = tmpAdminId;
        this.status = status;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getTmpUserId() {
        return tmpUserId;
    }

    public void setTmpUserId(String tmpUserId) {
        this.tmpUserId = tmpUserId;
    }

    public String getTmpAdminId() {
        return tmpAdminId;
    }

    public void setTmpAdminId(String tmpAdminId) {
        this.tmpAdminId = tmpAdminId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
   
    
}
