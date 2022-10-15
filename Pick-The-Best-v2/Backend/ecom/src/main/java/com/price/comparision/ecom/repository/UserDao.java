package com.price.comparision.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.model.User;

@Repository
public interface UserDao extends CrudRepository<User,String> {

	@Query("FROM User WHERE email = :email")
	List<User> findUserByEmail(String email);
}
