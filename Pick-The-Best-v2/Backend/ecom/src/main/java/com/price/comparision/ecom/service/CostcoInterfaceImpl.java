package com.price.comparision.ecom.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.price.comparision.ecom.bean.GlobalSearchItem;
import com.price.comparision.ecom.bean.ItemDetail;
import com.price.comparision.ecom.config.DriverFactory;
import com.price.comparision.ecom.constants.BusinessConstants;
import com.price.comparision.ecom.constants.DatabaseConstants;

@Component
public class CostcoInterfaceImpl implements CostcoInterface {
	
	@Value("${costco.url}")
	private String uri;

	@Value("${costco.store}")
	private String store;
	
	@Value("${costco.retry}")
	private Integer retryAttempts;
	
	@Value("${costco.global.search.url}")
	private String baseUri;
	
	@Override
	@Cacheable(value="localSearchCache", key = "'CostcoStore'+#id")
	public ItemDetail getItemInfo(String id,int retry) {
		
		ChromeDriver driver = DriverFactory.getInstance().getDriver();
		try {

			String url = uri.replace("replaceIdHere", id);
			ItemDetail detail= new ItemDetail();
			
			if(id.equals(DatabaseConstants.NOT_AVIALABLE)) {
				detail.setStoreName(store);
			}else {
				detail.setStoreName(store);
				detail.setUrl(url);
				try {
					driver.get(url);
					final List<WebElement> words = driver.findElementsById("pull-right-price");
					
					for (WebElement w : words) {
						detail.setPrice(w.getText().replace("\n", ""));
					} 
					
					if(detail.getPrice() != BusinessConstants.NOT_AVAILABLE) {
						return detail;
					}else if(retry < retryAttempts){
						return getItemInfo(id,++retry);
					}	
				}catch(Exception e) {
					//add custom logger here
				}
			}	
			return detail;
		}catch(Exception ex) {
			//add custom Exceptional Handling and Logging
		}finally {
			driver.quit();
		}
		return null;
	}


	@Override
	@Cacheable(value = "globalSearchCache", key = "'CostcoStore'+#searchToken")
	public List<GlobalSearchItem> getGlobalSearchInfo(String searchToken) {
		
		ChromeDriver driver = DriverFactory.getInstance().getDriver();
		
		try {
			String url = baseUri.concat(searchToken.replaceAll(" ", "+").trim());
			List<GlobalSearchItem> costcoItems = new ArrayList<GlobalSearchItem>();

			int attempts = 0;
			driver.get(url);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Long height = (Long) js.executeScript("return document.documentElement.scrollHeight");
			Integer tmp = 400;

			while (height > 0) {
				try{
					String scroller = String.format("window.scrollTo(0, %d)", tmp);
					((JavascriptExecutor) driver).executeScript(scroller);
					tmp += 400;
					height -= 400;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// Custom Logging and Exception Handling
					}
				}catch(Exception ex) {
					//add custom logging and exception handling
				}
				
			}
			
			List<WebElement> products = new ArrayList<WebElement>();
			
			try {
				products = driver.findElementsByXPath("//*[contains(@class, 'product')]");
			}catch(Exception ex) {
				//add custom logging and exception handling
			}
			
			while (products.isEmpty() && attempts < 10) {
				products = driver.findElementsByXPath("//*[contains(@class, 'product')]");
				attempts++;
			}
			
			for (int i = 0; i < products.size(); i++) {
				
				WebElement e = products.get(i);
				GlobalSearchItem gsi = new GlobalSearchItem();
				gsi.setStore(store);
				
				try {
					List<WebElement> productName = e.findElements(By.className("description"));
					gsi.setProductName(productName.get(0).getText());
				}catch(Exception ex) {
					//add custom logging and Exception Handling
				}
				
				try {
					List<WebElement> price = e.findElements(By.className("price"));
					gsi.setPrice(price.get(0).getText());
				}catch(Exception ex) {
					//add custom logging and Exception Handling
				}
				
				try {
					List<WebElement> rating = e.findElements(By.className("offscreen"));
					String ratingString = rating.get(0).getText();
					if(ratingString.contains("0 reviews")) {
						gsi.setRating(BusinessConstants.NOT_AVAILABLE);
					}else {
						ratingString = ratingString.replace("Rated ", "").substring(0,3).concat("/5").replace(" o", "");
						gsi.setRating(ratingString);
					}	
				}catch(Exception ex) {
					//add custom logging and Exception Handling
				}
				
				try {
					List<WebElement> imageUrls = e.findElements(By.className("img-responsive"));
					gsi.setImageUrl(imageUrls.get(0).getAttribute("src"));
				}catch(Exception ex) {
					//add custom logging and Exception Handling
				}
				
				try {
					String prodcutUrl = e.findElement(By.xpath(".//a")).getAttribute("href");
					gsi.setUrl(prodcutUrl);
				} catch (Exception ex) {
					// add Custom Logging and exception handling here
				}
				if(gsi.isValidItem())
					costcoItems.add(gsi);
				
			}
			Set<GlobalSearchItem> productSet = new LinkedHashSet<GlobalSearchItem>();
			for(GlobalSearchItem g : costcoItems) {
				productSet.add(g);
			}
			costcoItems = new ArrayList<GlobalSearchItem>();
			for(GlobalSearchItem gi : productSet) {
				costcoItems.add(gi);
			}
			if(!costcoItems.isEmpty())
				return costcoItems;
			else
				return null;
		}catch(Exception Ex) {
			
		}finally {
			driver.quit();
		}
		return null;	
	}

}
