package com.price.comparision.ecom.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.model.AdminStats;
import com.price.comparision.ecom.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
	
	@Query("from Request where requestDate between :startDate and :endDate and requestStatus='N'")
	List<Request> getRequests(Date startDate, Date endDate);
	
	@Query("select new com.price.comparision.ecom.model.AdminStats(requestDate, count(1)) from Request where requestStatus = 'N' group by requestDate order by requestDate desc")
	List<AdminStats> getStats();
	
}