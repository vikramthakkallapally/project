package com.price.comparision.ecom.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Request {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	private String productname;
	
	private String productDescription;
	
	private String email;
	
	private Date requestDate;
	
	private String requestStatus;
	
	private String closedBy;

	public Request() {
		this.requestDate = new Date();
		this.requestStatus = "N";
	}

	public String getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", username=" + username + ", productname=" + productname + ", productDescription="
				+ productDescription + ", email=" + email + ", requestDate=" + requestDate + ", requestStatus="
				+ requestStatus + ", closedBy=" + closedBy + "]";
	}

}
