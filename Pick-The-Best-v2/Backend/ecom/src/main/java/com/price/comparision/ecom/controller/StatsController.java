package com.price.comparision.ecom.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.price.comparision.ecom.bean.SearchStats;
import com.price.comparision.ecom.service.DatabaseService;



@RestController
@RequestMapping("/stats")
public class StatsController {
	
	@Autowired
	private DatabaseService databaseService;
	
	@GetMapping("/getStats")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,pattern ="dd-MM-yyy")
	public List<SearchStats> getStats(@RequestParam("startDate") Long startDate, @RequestParam("endDate") Long endDate){
		return databaseService.getStats(new Date(startDate), new Date(endDate));
	}
	
}
