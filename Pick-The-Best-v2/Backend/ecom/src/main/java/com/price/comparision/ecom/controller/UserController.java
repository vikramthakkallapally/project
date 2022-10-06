package com.price.comparision.ecom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.price.comparision.ecom.bean.User;
import com.price.comparision.ecom.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping({"/user/registerNewUser"})
	public User registerNewUser(@RequestBody User user) {
		System.out.println(user);
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
	

}
