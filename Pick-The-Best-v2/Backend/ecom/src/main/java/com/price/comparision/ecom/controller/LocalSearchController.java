package com.price.comparision.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.price.comparision.ecom.bean.Item;
import com.price.comparision.ecom.bean.ProductDetails;
import com.price.comparision.ecom.service.DatabaseService;
import com.price.comparision.ecom.service.ProductDetailService;

@RestController
@RequestMapping("/local")
public class LocalSearchController {
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@Autowired
	private DatabaseService databaseService;
	
	@GetMapping("/getProductsInfo")
	public List<ProductDetails> getProductDetails(@RequestParam(name = "searchToken") String searchToken) {
		return productDetailService.getProductDetails(searchToken);
	}
	
	@GetMapping("/getItems")
	public List<Item>  getItems(@RequestParam(name = "searchToken") String searchToken){	
		return databaseService.findItemsForAdmin(searchToken);
	}
	
}
