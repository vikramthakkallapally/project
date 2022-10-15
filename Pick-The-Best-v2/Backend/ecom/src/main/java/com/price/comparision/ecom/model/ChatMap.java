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

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatid) {
        this.chatId = chatid;
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
