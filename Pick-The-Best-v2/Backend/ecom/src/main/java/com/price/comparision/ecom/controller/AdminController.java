package com.price.comparision.ecom.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.price.comparision.ecom.model.AdminStats;
import com.price.comparision.ecom.model.Contactus;
import com.price.comparision.ecom.model.Item;
import com.price.comparision.ecom.model.Request;
import com.price.comparision.ecom.service.AdminService;
import com.price.comparision.ecom.service.DatabaseService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private DatabaseService databaseService;
	
	@PostMapping("/saveRequest")
	public Long saveProductRequest(@RequestBody Request req) {
		return adminService.saveProductRequest(req);
	}
	
	@PostMapping("/contactus")
	public ResponseEntity<Object> saveContactus(@RequestBody Contactus cts) {
		adminService.submitFeedback(cts);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/addItems")
	@PreAuthorize("hasRole('ADMIN')")
	public Integer  saveItems(@RequestParam(name = "username") String username,@RequestBody List<Item> items){	
		return databaseService.saveItems(items,username);
	}
	
	@GetMapping("/getRequests")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Request> findRequests(@RequestParam("startDate") Long startDate, @RequestParam("endDate") Long endDate){
		return adminService.findRequests(new Date(startDate), new Date(endDate));
	}
	
	@GetMapping("/getItems")
	public List<Item> findRequests(@RequestParam("searchToken") String searchToken){
		return databaseService.findItemsForAdmin(searchToken);
	}
	
	@GetMapping("deleteItem")
	@PreAuthorize("hasRole('ADMIN')")
	public Long deleteItemById(@RequestParam("id") Long id){
		return databaseService.deleteItem(id);
	}
	
	@GetMapping("/getRequestStats")
	@PreAuthorize("hasRole('ADMIN')")
	public List<AdminStats> getRequestStats(){
		return adminService.getRequestStats();
	}
	
	@GetMapping("/getFeedbacks")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Contactus> findFeedBacks(@RequestParam("startDate") Long startDate, @RequestParam("endDate") Long endDate){
		return adminService.getFeedbacks(new Date(startDate), new Date(endDate));
	}
	
}
