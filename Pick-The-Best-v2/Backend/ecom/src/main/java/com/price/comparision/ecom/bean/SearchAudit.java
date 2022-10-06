package com.price.comparision.ecom.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.price.comparision.ecom.enums.SearchTypeEnum;

@Entity
public class SearchAudit {
	
	@Id
	@GeneratedValue
	private Integer Id;
	
	private Integer searchCount;
	
	private String searchToken;
	
	private Date searchDate;

	public SearchAudit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchAudit(SearchTypeEnum searchType, Integer searchCount, String searchToken,
			Date searchDate) {
		super();
		this.searchCount = searchCount;
		this.searchToken = searchToken;
		this.searchDate = searchDate;
	}

	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		Id = id;
	}

	public Integer getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}

	public String getSearchToken() {
		return searchToken;
	}

	public void setSearchToken(String searchToken) {
		this.searchToken = searchToken;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	@Override
	public String toString() {
		return "SearchAudit [Id=" + Id + ", searchCount=" + searchCount + ", searchToken=" + searchToken
				+ ", searchDate=" + searchDate + "]";
	}
}
