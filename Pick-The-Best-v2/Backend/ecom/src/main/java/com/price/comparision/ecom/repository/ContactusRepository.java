package com.price.comparision.ecom.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.model.Contactus;

@Repository
public interface ContactusRepository extends JpaRepository<Contactus,Long> {
	
	@Query("from Contactus where feedbackDate between :startDate and :endDate")
	List<Contactus> getFeedback(Date startDate, Date endDate);

}
