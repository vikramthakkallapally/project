package com.price.comparision.ecom.model;

import java.util.ArrayList;
import java.util.List;

import com.price.comparision.ecom.constants.BusinessConstants;

public class ProductDetails {
	
	private String description;
	
	private List<ItemDetail> itemDetails;

	public ProductDetails() {
		List<ItemDetail> itemDetails = new ArrayList<ItemDetail>();
		this.itemDetails = itemDetails;
		this.description = BusinessConstants.DESCTIPTION_NOT_AVAILABLE;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ItemDetail> getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(List<ItemDetail> itemDetails) {
		this.itemDetails = itemDetails;
	}

	@Override
	public String toString() {
		return "ProductDetails [description=" + description + ", itemDetails=" + itemDetails + "]";
	}

}
