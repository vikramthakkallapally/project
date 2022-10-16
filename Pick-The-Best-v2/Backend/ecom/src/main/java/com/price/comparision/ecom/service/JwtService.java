package com.price.comparision.ecom.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.price.comparision.ecom.exception.InvalidCredentialsException;
import com.price.comparision.ecom.model.JwtRequest;
import com.price.comparision.ecom.model.JwtResponse;
import com.price.comparision.ecom.model.User;
import com.price.comparision.ecom.repository.UserDao;
import com.price.comparision.ecom.util.JwtUtil;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService{
	
	@Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName,userPassword);
		UserDetails userDetails = loadUserByUsername(userName);
		
		User user = userDao.findById(userName).get();
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		
		return new JwtResponse(user,newGeneratedToken);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findById(username).get();
		
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(
					user.getUserName(),
					user.getUserPassword(),
					getAuthorities(user)
				);
		}else {
			throw new UsernameNotFoundException("username is not valid");
		}
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(User user) {
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		user.getRoles().forEach(role  -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
			}
		);
		
		return authorities;
	}
	
	
	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
			
		}catch(DisabledException e) {
			throw new InvalidCredentialsException();
		}catch(BadCredentialsException e) {
			throw new InvalidCredentialsException();
		}
		
	}

}
