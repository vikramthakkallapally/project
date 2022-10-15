package com.price.comparision.ecom.model;

public class UpdatePasswordRequest {

	private String email;
	
	private String token;
	
	private String password;

	public UpdatePasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdatePasswordRequest(String email, String token, String password) {
		super();
		this.email = email;
		this.token = token;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
