package com.price.comparision.ecom.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Item {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String Description;
	
	private String amazonId;
	
	private String costcoId;
	
	private String krogerId;
	
	private String targetId;
	
	@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
	private String username;

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(String description, String amazonId, String costcoId, String krogerId, String targetId,
			String username) {
		super();
		Description = description;
		this.amazonId = amazonId;
		this.costcoId = costcoId;
		this.krogerId = krogerId;
		this.targetId = targetId;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmazonId() {
		return amazonId;
	}

	public void setAmazonId(String amazonId) {
		this.amazonId = amazonId;
	}

	public String getKrogerId() {
		return krogerId;
	}

	public void setKrogerId(String krogerId) {
		this.krogerId = krogerId;
	}

	public String getCostcoId() {
		return costcoId;
	}

	public void setCostcoId(String costcoId) {
		this.costcoId = costcoId;
	}


	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", Description=" + Description + ", amazonId=" + amazonId + ", costcoId=" + costcoId
				+ ", krogerId=" + krogerId + ", targetId=" + targetId + ", username=" + username + "]";
	}
}
