package com.price.comparision.ecom.scheduledTasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.price.comparision.ecom.constants.BusinessConstants;
import com.price.comparision.ecom.model.Item;
import com.price.comparision.ecom.service.AmazonInterface;
import com.price.comparision.ecom.service.CostcoInterface;
import com.price.comparision.ecom.service.DatabaseService;
import com.price.comparision.ecom.service.KrogerInterface;
import com.price.comparision.ecom.service.ProductDetailService;
import com.price.comparision.ecom.service.TargetInterface;



@Component
@EnableCaching
public class SearchScheduledTask {
	
	@Value("${local.search.batch.size}")
	private Integer batchSize;
	
	@Autowired
	DatabaseService dataBaseService;
	
	@Autowired
	ProductDetailService productDetailsService;
	
	@Autowired
	private AmazonInterface amazonService;
	
	@Autowired
	private CostcoInterface costcoService;
	
	@Autowired
	private KrogerInterface krogerService;
	
	@Autowired
	private TargetInterface targetService;
	
	
	@Scheduled(initialDelay = 500000000, fixedDelay = 10000)
	public void globalSearchTask() {
		List<String> searchTokens = dataBaseService.getSearchTokens();
		for(String token : searchTokens) {
			productDetailsService.getAmazonGlobalSearchResult(token);
			productDetailsService.getTargetGlobalSearchResult(token);
			productDetailsService.getCostcoGlobalSearchResult(token);
			productDetailsService.getKrogerGlobalSearchResult(token);
			dataBaseService.deleteSearchTokenCount(token);
		}
		
	}
	
	@Scheduled(initialDelay = 500000000, fixedDelay = 50000)
	public void localSearchTask() {
		
		Long startId = (long) 0;	
		Long maxId = dataBaseService.getMaxId();
		
		while(startId < maxId) {
			List<Item> items = dataBaseService.getItemsById(startId, startId+batchSize);
			for(Item i: items) {	
				if(i.getAmazonId() != BusinessConstants.NOT_AVAILABLE)
					amazonService.getItemInfo(i.getAmazonId(),0);
				if(i.getCostcoId() != BusinessConstants.NOT_AVAILABLE)
					costcoService.getItemInfo(i.getCostcoId(),0);
				if(i.getCostcoId() != BusinessConstants.NOT_AVAILABLE)
					krogerService.getItemInfo(i.getKrogerId(),0);
				if(i.getCostcoId() != BusinessConstants.NOT_AVAILABLE)
					targetService.getItemInfo(i.getTargetId(),0);
			}
			startId += batchSize;
		}
	}
	
}
