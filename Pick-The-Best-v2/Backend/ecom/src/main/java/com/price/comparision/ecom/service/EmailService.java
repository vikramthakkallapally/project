package com.price.comparision.ecom.service;

import org.springframework.stereotype.Service;

import com.price.comparision.ecom.model.Email;

@Service
public interface EmailService {
	boolean sendMail(Email email);
}
