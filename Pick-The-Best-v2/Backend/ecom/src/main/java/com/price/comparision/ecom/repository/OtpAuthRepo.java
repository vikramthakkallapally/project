package com.price.comparision.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.model.OtpAuth;

@Repository
public interface OtpAuthRepo extends CrudRepository<OtpAuth,String> {
	@Query("from OtpAuth where email = :email")
	List<OtpAuth> getauthToken(String email);
}
