package com.price.comparision.ecom.bean;

import org.springframework.stereotype.Component;

@Component
public class SearchStats {
	
	private String searchToken;
	
	private Long count;

	public SearchStats() {
		super();
	}

	public SearchStats(String searchToken, Long count) {
		this.searchToken = searchToken;
		this.count = count;
	}

	public String getSearchToken() {
		return searchToken;
	}

	public void setSearchToken(String searchToken) {
		this.searchToken = searchToken;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "SearchStats [searchToken=" + searchToken + ", count=" + count + "]";
	}
	
}
