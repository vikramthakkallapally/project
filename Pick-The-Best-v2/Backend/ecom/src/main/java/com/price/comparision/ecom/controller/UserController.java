package com.price.comparision.ecom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.price.comparision.ecom.exception.GeneralBusinessException;
import com.price.comparision.ecom.exception.InvalidOtpException;
import com.price.comparision.ecom.model.EmailAuthRequest;
import com.price.comparision.ecom.model.ErrorServiceResponse;
import com.price.comparision.ecom.model.UpdatePasswordRequest;
import com.price.comparision.ecom.model.User;
import com.price.comparision.ecom.service.UserServiceImpl;


@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping({"/user/registerNewUser"})
	public User registerNewUser(@RequestBody User user) {
		return userService.registerNewUser(user);
	}
	
	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "This URL is accessbile only to Admin";
	}
	
	@GetMapping({"/forUser"})
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "This URL ia accessible only to User";
	}
	
	@GetMapping({"/forAnyUser"})
	@PreAuthorize("hasAnyRole('User','Admin')")
	public String forAnyUser() {
		return "This URL ia accessible only to User";
	}
	
	@GetMapping({"/reset/sendotp"})
	public ResponseEntity<Void> sendOtp(@RequestParam String email) {
		try {
			userService.sendOtp(email);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(Exception ex) {
		    throw new GeneralBusinessException(new ErrorServiceResponse(HttpStatus.BAD_REQUEST,"invalid email"));
		}
	}
	
	@PostMapping({"/reset/verifyOtp"})
	public ResponseEntity<Void> verifyOtp(@RequestBody EmailAuthRequest otpAuthRequest) {
		if(userService.verifyOtp(otpAuthRequest)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			throw new InvalidOtpException();
		}
		
	}
	
	@PostMapping("/reset/password")
	public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordRequest updatePassword) {
		
		if(userService.updatePassword(updatePassword))
			return ResponseEntity.status(HttpStatus.OK).build();
		else
			throw new GeneralBusinessException(new ErrorServiceResponse(HttpStatus.BAD_REQUEST,"invalid password"));
		
	}

}
