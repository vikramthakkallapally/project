package com.price.comparision.ecom.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contactus {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;

	private String email;
	
	private String feedback;

	private Date feedbackDate;
	
	public Contactus() {
		this.feedbackDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Contactus [id=" + id + ", username=" + username + ", email=" + email + ", feedback=" + feedback
				+ ", feedbackDate=" + feedbackDate + "]";
	}
	
}
