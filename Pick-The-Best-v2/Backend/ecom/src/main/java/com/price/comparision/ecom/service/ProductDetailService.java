package com.price.comparision.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.price.comparision.ecom.constants.BusinessConstants;
import com.price.comparision.ecom.model.GlobalSearchDetails;
import com.price.comparision.ecom.model.GlobalSearchItem;
import com.price.comparision.ecom.model.Item;
import com.price.comparision.ecom.model.ProductDetails;
import com.price.comparision.ecom.repository.ItemRepository;

@Service
public class ProductDetailService {
	
	@Autowired
	private AmazonInterface amazonService;
	
	@Autowired
	private CostcoInterface costcoService;
	
	@Autowired
	private KrogerInterface krogerService;
	
	@Autowired
	private TargetInterface targetService;
	
	@Autowired
	private ItemRepository itemrepository;
	
	@Autowired
	private DatabaseService searchAuditService;
	
	@Value("${local.search.render.default}")
	private String localSearchDefault; 
	
	
	public List<ProductDetails> getProductDetails(String searchToken) {
		
		if(searchToken == null || searchToken.isEmpty())
			searchToken = localSearchDefault;
		
		List<Item> items = itemrepository.searchByDesctiption(searchToken);
		searchAuditService.saveSearchToken(searchToken);
		List<ProductDetails> details = new ArrayList<ProductDetails>();
		for(Item i: items) {
			ProductDetails pd = new ProductDetails();
			pd.setDescription(i.getDescription());
			
			if(i.getAmazonId() != BusinessConstants.NOT_AVAILABLE)
				pd.getItemDetails().add(amazonService.getItemInfo(i.getAmazonId(),0));
			if(i.getCostcoId() != BusinessConstants.NOT_AVAILABLE)
				pd.getItemDetails().add(costcoService.getItemInfo(i.getCostcoId(),0));
			if(i.getCostcoId() != BusinessConstants.NOT_AVAILABLE)
				pd.getItemDetails().add(krogerService.getItemInfo(i.getKrogerId(),0));
			if(i.getCostcoId() != BusinessConstants.NOT_AVAILABLE)
				pd.getItemDetails().add(targetService.getItemInfo(i.getTargetId(),0));
			details.add(pd);
		}
		return details;	
	}
	
	public GlobalSearchDetails getGlobalProductsInfo(String searchToken) {
		
		int attempts = 5;
		GlobalSearchDetails details = new GlobalSearchDetails();
		
		details.setAmazonSearchResults(amazonService.getGlobalSearchInfo(searchToken));
		while( details.getAmazonSearchResults() == null && attempts <= 5) {
			details.setKrogerSearchResults(amazonService.getGlobalSearchInfo(searchToken));
			attempts++;
		}
		
		attempts=0;
		while( details.getKrogerSearchResults() == null && attempts <= 5) {
			details.setKrogerSearchResults(krogerService.getGlobalSearchInfo(searchToken));
			attempts++;
		}
		
		attempts = 0;
		while( details.getTargetSearchResults() == null && attempts <= 5) {
			details.setTargetSearchResults(targetService.getGlobalSearchInfo(searchToken));
			attempts++;
		}
		
		attempts = 0;
		while(details.getCostcoSearchResults() == null  && attempts <= 5) {
			details.setCostcoSearchResults(costcoService.getGlobalSearchInfo(searchToken));
		}
		
		return details;
	}
	
	public List<GlobalSearchItem> getAmazonGlobalSearchResult(String searchToken){
		int attempts = 0;
		List<GlobalSearchItem> items = new ArrayList<GlobalSearchItem>();
		searchAuditService.saveSearchToken(searchToken);
		while(items.isEmpty() && attempts <= 5) {
			List<GlobalSearchItem> tmp = amazonService.getGlobalSearchInfo(searchToken);
			if(tmp != null)
				items= tmp;
			searchToken.concat(" ");
			attempts++;
		}
		return items;
	}
	
	public List<GlobalSearchItem> getKrogerGlobalSearchResult(String searchToken){
		
		int attempts = 0;
		List<GlobalSearchItem> items = new ArrayList<GlobalSearchItem>();
		
		while(items.isEmpty() && attempts <= 2) {
			List<GlobalSearchItem> tmp = krogerService.getGlobalSearchInfo(searchToken);
			if(tmp != null)
				items = tmp;
			searchToken = " ".concat(searchToken);
			attempts++;
		}
		
		return items;
	}
	
	public List<GlobalSearchItem> getCostcoGlobalSearchResult(String searchToken){
		int attempts = 0;
		List<GlobalSearchItem> items = new ArrayList<GlobalSearchItem>();
		while(items.isEmpty() && attempts <= 5) {
			List<GlobalSearchItem> tmp = costcoService.getGlobalSearchInfo(searchToken);
			if(tmp != null)
				items= tmp;
			searchToken.concat(" ");
			attempts++;
		}
		return items;
	}
	
	public List<GlobalSearchItem> getTargetGlobalSearchResult(String searchToken){
		
		int attempts = 5;
		List<GlobalSearchItem> items = new ArrayList<GlobalSearchItem>();
		while(items.isEmpty() && attempts <= 5) {
			List<GlobalSearchItem> tmp = targetService.getGlobalSearchInfo(searchToken);
			if(tmp != null)
				items= tmp;
			searchToken.concat(" ");
			attempts++;
		}
		return items;
	}

}
