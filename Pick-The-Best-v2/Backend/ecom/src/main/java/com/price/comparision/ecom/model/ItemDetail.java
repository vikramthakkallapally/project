package com.price.comparision.ecom.model;

import com.price.comparision.ecom.constants.BusinessConstants;

public class ItemDetail {
	
	
	private String storeName;
	
	private String price;
	
	private String rating;
	
	private String url;

	public ItemDetail() {
		this.storeName = BusinessConstants.NOT_AVAILABLE;
		this.price = BusinessConstants.NOT_AVAILABLE;
		this.rating = BusinessConstants.NOT_AVAILABLE;
		this.url = BusinessConstants.NOT_AVAILABLE;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ItemDetail [storeName=" + storeName + ", price=" + price + ", rating=" + rating + ", url=" + url + "]";
	}

}
