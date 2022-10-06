package com.price.comparision.ecom.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.price.comparision.ecom.bean.AdminStats;
import com.price.comparision.ecom.bean.Contactus;
import com.price.comparision.ecom.bean.Request;
import com.price.comparision.ecom.repository.ContactusRepository;
import com.price.comparision.ecom.repository.RequestRepository;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private ContactusRepository contactUsRepository;
	
	public Long saveProductRequest(Request req) {
		return requestRepository.save(req).getId();
	}
	
	public void submitFeedback(Contactus cts) {
		contactUsRepository.save(cts);
	}
	
	public List<Request> findRequests(Date startDate, Date endDate){
		return requestRepository.getRequests(startDate, endDate);
	}
	
	public List<AdminStats> getRequestStats(){
		return requestRepository.getStats();
	}
	
	public List<Contactus> getFeedbacks(Date startDate, Date endDate){
		return contactUsRepository.getFeedback(startDate, endDate);
	}
	
	
}
