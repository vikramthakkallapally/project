package com.price.comparision.ecom.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.bean.Item;
import com.price.comparision.ecom.bean.SearchAudit;
import com.price.comparision.ecom.bean.SearchStats;

@Repository
public interface SearchAuditRepository extends CrudRepository<SearchAudit,Long> {
	
	@Query("FROM Item i WHERE i.Description LIKE %:search%")
	List<Item> searchByDesctiption(@Param("search") String search);
	
	@Query("select searchToken from SearchAudit where searchDate > :date group by searchToken order by sum(searchCount) desc")
	List<String> findTopGlobalSearchByDate(Date date);
	
	@Query("from SearchAudit where searchDate = :date and searchToken = :searchToken")
	List<SearchAudit> findsearchToken(Date date,String searchToken);
	
	@Query("update SearchAudit set searchCount = searchCount-1 where searchDate= :date and searchToken = :searchToken")
	void decreaseSearchTokenCount(Date date,String searchToken);
	
	@Query("select new com.price.comparision.ecom.bean.SearchStats(searchToken, sum(searchCount)) from SearchAudit where searchDate between :startDate and :endDate group by searchToken order by sum(searchCount) desc")
	List<SearchStats> getStats(Date startDate, Date endDate);
	
}