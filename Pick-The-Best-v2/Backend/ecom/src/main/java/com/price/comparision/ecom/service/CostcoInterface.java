package com.price.comparision.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.price.comparision.ecom.model.GlobalSearchItem;
import com.price.comparision.ecom.model.ItemDetail;

@Service
public interface CostcoInterface {
	
	public ItemDetail getItemInfo(String id,int retry);
	
	public List<GlobalSearchItem> getGlobalSearchInfo(String token);
	
}
