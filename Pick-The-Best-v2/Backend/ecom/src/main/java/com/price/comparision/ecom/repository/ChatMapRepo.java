package com.price.comparision.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.model.ChatMap;

@Repository
public interface ChatMapRepo extends JpaRepository<ChatMap,Long>{
    @Query("from ChatMap where adminname = :adminname and status = 0 ")
    List<ChatMap> checkAdminExist(String adminname);
    
    @Query("from ChatMap where username = :username and status = 0 ")
    List<ChatMap> checkUserExist(String username);
    
    @Query("from ChatMap where status = 0 and username = null")
    List<ChatMap> findAvailableAdmin();
    
    @Query("from ChatMap where status = 0 and tmpUserId = :tmpUserId")
    List<ChatMap> findChat(String tmpUserId);
    
    @Query(value="from ChatMap where chatId = :chatId and status = 0")
    List<ChatMap> isChatActive(Long chatId);
    
}
