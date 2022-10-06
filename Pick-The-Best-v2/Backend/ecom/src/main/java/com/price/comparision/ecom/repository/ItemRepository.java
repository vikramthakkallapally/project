package com.price.comparision.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.bean.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
	
	@Query("FROM Item i WHERE i.Description LIKE %:search% or i.amazonId LIKE  %:search% or i.costcoId LIKE"
			+ "  %:search%  or i.targetId LIKE  %:search% or i.krogerId LIKE  %:search% ")
	List<Item> searchByDesctiption(String search);
	
	@Query("FROM Item i WHERE i.id between :start and :end")
	List<Item> getItemsById(Long start, Long end);
	
	
	@Query("select max(id) from Item")
	Long findMaxId();
	
	@Query("from Item where description like %:searchToken%  or amazonId like  %:searchToken% or costcoId like %:searchToken% or targetId like %:searchToken%")
	List<Item> getItemsForAdminSearch(String searchToken);

}
