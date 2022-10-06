package com.price.comparision.ecom.bean;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GlobalSearchItem {
	
	private String productName;
	
	private String Price;
	
	private String store;
	
	private String rating;
	
	private String imageUrl;
	
	private String url;

	public GlobalSearchItem() {
		super();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@JsonIgnore
	public boolean isValidItem() {
		boolean isvalid = true;
		
		if(this.productName == null || this.Price == null || this.store == null || this.Price.isEmpty() || this.productName.isEmpty()) {
			isvalid = false;
		}
		
		return isvalid;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "GlobalSearchItem [productName=" + productName + ", Price=" + Price + ", store=" + store + ", rating="
				+ rating + ", imageUrl=" + imageUrl + ", url=" + url + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Price, imageUrl, productName, rating, store, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GlobalSearchItem other = (GlobalSearchItem) obj;
		return Objects.equals(Price, other.Price) && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(productName, other.productName) && Objects.equals(rating, other.rating)
				&& Objects.equals(store, other.store) && Objects.equals(url, other.url);
	}

	
}
