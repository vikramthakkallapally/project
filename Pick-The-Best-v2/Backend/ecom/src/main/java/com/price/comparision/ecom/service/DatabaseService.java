package com.price.comparision.ecom.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.price.comparision.ecom.constants.DatabaseConstants;
import com.price.comparision.ecom.model.Item;
import com.price.comparision.ecom.model.SearchAudit;
import com.price.comparision.ecom.model.SearchStats;
import com.price.comparision.ecom.repository.ItemRepository;
import com.price.comparision.ecom.repository.SearchAuditRepository;

@Transactional
@Service
public class DatabaseService {

	@Autowired
	private SearchAuditRepository searchRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	public List<String> getSearchTokens() {
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusDays(DatabaseConstants.searchDateLimit);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date utilDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		return searchRepository.findTopGlobalSearchByDate(utilDate);

	}

	public void saveSearchToken(String searchToken) {
		
		List<SearchAudit> item;
		item = searchRepository.findsearchToken(getDate(), searchToken);
		if (item.isEmpty()) {
			SearchAudit audit = new SearchAudit();
			audit.setSearchCount(1);
			audit.setSearchDate(getDate());
			audit.setSearchToken(searchToken);
			searchRepository.save(audit);
		} else {
			SearchAudit audit = item.get(0);
			audit.setSearchCount(audit.getSearchCount() + 1);
			searchRepository.save(audit);
		}
	}

	public void deleteSearchTokenCount(String searchToken) {
		
		List<SearchAudit> item;
		item = searchRepository.findsearchToken(getDate(), searchToken);
		if (!item.isEmpty()) {
			SearchAudit audit = item.get(0);
			audit.setSearchCount(audit.getSearchCount() - 1);
			searchRepository.save(audit);
		}
	}
	
	public int saveItems(List<Item> items, String username) {
		items.forEach(it -> it.setUsername(username));
		return itemRepository.saveAll(items).size();
	}
	
	public List<Item> findItemsForAdmin(String searchToken){
		return itemRepository.getItemsForAdminSearch(searchToken);
	}
	
	public Long deleteItem(Long id) {
		 itemRepository.deleteById(id);
		 return id;
	}
	
	public List<SearchStats> getStats(Date startDate,Date endDate){
		return searchRepository.getStats(startDate, endDate);
	}
	
	public Long getMaxId() {
		return itemRepository.findMaxId();
	}
	
	public List<Item> getItemsById(Long start, Long end){
		return itemRepository.getItemsById(start, end);
	}
	
	public Date getDate() {
		   Calendar calendar = Calendar.getInstance();
		   calendar.set(Calendar.HOUR_OF_DAY, 0);
		   calendar.set(Calendar.MINUTE, 0);
		   calendar.set(Calendar.SECOND, 0);
		   calendar.set(Calendar.MILLISECOND, 0);
		   return calendar.getTime();
	}
	
}
