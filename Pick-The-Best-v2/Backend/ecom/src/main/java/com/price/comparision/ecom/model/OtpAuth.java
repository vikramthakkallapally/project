package com.price.comparision.ecom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="otp_auth")
public class OtpAuth {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String email;
	
	@Column
	private String token;

	
	public OtpAuth() {
		super();
	}

	public OtpAuth(String email, String token) {
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
