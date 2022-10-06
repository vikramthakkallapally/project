package com.price.comparision.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.bean.User;

@Repository
public interface UserDao extends CrudRepository<User,String> {

}
