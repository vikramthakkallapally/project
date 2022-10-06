package com.price.comparision.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.price.comparision.ecom.bean.GlobalSearchDetails;
import com.price.comparision.ecom.bean.GlobalSearchItem;
import com.price.comparision.ecom.service.ProductDetailService;

@RestController
@RequestMapping("/global")
public class GlobalSearchController {
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@GetMapping("/getProductsInfo")
	public GlobalSearchDetails getProductDetails(@RequestParam(name = "searchToken") String searchToken) {
		return productDetailService.getGlobalProductsInfo(searchToken);
	}
	
	@GetMapping("/amazon/getProductsInfo")
	public List<GlobalSearchItem> getAmazonProductDetails(@RequestParam(name = "searchToken") String searchToken) {	
		return productDetailService.getAmazonGlobalSearchResult(searchToken);
	}
	
	@GetMapping("/kroger/getProductsInfo")
	public List<GlobalSearchItem> getkrogerProductDetails(@RequestParam(name = "searchToken") String searchToken) {
		return productDetailService.getKrogerGlobalSearchResult(searchToken);
	}

	@GetMapping("/target/getProductsInfo")
	public List<GlobalSearchItem> getTargetProductDetails(@RequestParam(name = "searchToken") String searchToken) {	
		return productDetailService.getTargetGlobalSearchResult(searchToken);	
	}

	@GetMapping("/costco/getProductsInfo")
	public List<GlobalSearchItem> getCostcoProductDetails(@RequestParam(name = "searchToken") String searchToken) {
		return productDetailService.getCostcoGlobalSearchResult(searchToken);	
	}

}
