package com.price.comparision.ecom.model;


public class EmailAuthRequest {
	
	private String email;
	
	private String token;

	public EmailAuthRequest() {
		super();
	}

	public EmailAuthRequest(String email, String token) {
		super();
		this.email = email;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
