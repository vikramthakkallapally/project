package com.price.comparision.ecom.bean;

import java.util.List;

public class GlobalSearchDetails {
	
	private List<GlobalSearchItem> amazonSearchResults;
	
	private List<GlobalSearchItem> krogerSearchResults;
	
	private List<GlobalSearchItem> targetSearchResults;
	
	private List<GlobalSearchItem> costcoSearchResults;

	public GlobalSearchDetails() {
		
	}

	public List<GlobalSearchItem> getAmazonSearchResults() {
		return amazonSearchResults;
	}

	public void setAmazonSearchResults(List<GlobalSearchItem> amazonSearchResults) {
		this.amazonSearchResults = amazonSearchResults;
	}

	public List<GlobalSearchItem> getKrogerSearchResults() {
		return krogerSearchResults;
	}

	public void setKrogerSearchResults(List<GlobalSearchItem> krogerSearchResults) {
		this.krogerSearchResults = krogerSearchResults;
	}

	public List<GlobalSearchItem> getCostcoSearchResults() {
		return costcoSearchResults;
	}

	public void setCostcoSearchResults(List<GlobalSearchItem> costcoSearchResults) {
		this.costcoSearchResults = costcoSearchResults;
	}

	public List<GlobalSearchItem> getTargetSearchResults() {
		return targetSearchResults;
	}

	public void setTargetSearchResults(List<GlobalSearchItem> targetSearchResults) {
		this.targetSearchResults = targetSearchResults;
	}

	@Override
	public String toString() {
		return "GlobalSearchDetails [amazonSearchResults=" + amazonSearchResults + ", krogerSearchResults="
				+ krogerSearchResults + ", targetSearchResults=" + targetSearchResults + ", costcoSearchResults="
				+ costcoSearchResults + "]";
	}

}
