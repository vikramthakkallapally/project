package com.price.comparision.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.price.comparision.ecom.bean.GlobalSearchItem;
import com.price.comparision.ecom.bean.ItemDetail;

@Service
public interface TargetInterface {
	public ItemDetail getItemInfo(String id,int retry);
	public List<GlobalSearchItem> getGlobalSearchInfo(String token);
}
