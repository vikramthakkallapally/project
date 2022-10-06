package com.price.comparision.ecom.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.price.comparision.ecom.bean.Role;
import com.price.comparision.ecom.bean.User;
import com.price.comparision.ecom.repository.RoleDao;
import com.price.comparision.ecom.repository.UserDao;



@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerNewUser(User user) {
		
		Role role = roleDao.findById("User").get();
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		
		userDao.save(user);
		
		return userDao.save(user);
	}

	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
