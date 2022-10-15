package com.price.comparision.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.model.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message,Long>{
    
}
