package com.price.comparision.ecom.service;

import java.util.ArrayList;
import java.util.List;

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
public class TargetInterfaceImpl implements TargetInterface {

	@Value("${target.url}")
	private String uri;

	@Value("${target.store}")
	private String store;

	@Value("${target.retry}")
	private Integer retryAttempts;

	@Value("${target.global.search.url}")
	private String globalSearchUrl;

	@Override
	@Cacheable(value = "localSearchCache", key = "'TargetStore'+#id")
	public ItemDetail getItemInfo(String id, int retry) {
		
		ChromeDriver driver = DriverFactory.getInstance().getDriver();
		
		try {
			final String url = uri + id;
			ItemDetail detail = new ItemDetail();
			detail.setStoreName(store);

			if (!id.equals(DatabaseConstants.NOT_AVIALABLE)) {
				detail.setUrl(url);
				try {
					driver.get(url);
					List<WebElement> words = driver.findElementsByXPath("//*[contains(@class, 'style__PriceFontSize')]");

					while (words.isEmpty() || retryAttempts < 10) {
						words = driver.findElementsByXPath("//*[contains(@class, 'style__PriceFontSize')]");
						retryAttempts++;
					}
					detail.setPrice(words.get(0).getText());

					words = driver.findElementsByXPath("//*[contains(@class, 'utils__ScreenReaderOnly')]");
					while (words.isEmpty() || retryAttempts < 10) {
						words = driver.findElementsByXPath("//*[contains(@class, 'utils__ScreenReaderOnly')]");
						retryAttempts++;
					}
					
					if(words.get(0).getText().contains(".")) {
						detail.setRating(words.get(0).getText().replace(" out of ", "").substring(0, 4).concat("/5"));
					}else {
						detail.setRating(words.get(0).getText().replace(" out of ", "").substring(0, 1).concat("/5"));
					}	
				} catch (Exception e) {
					// write custom loging and exception code
				}
			}
			return detail;
		}catch(Exception ex) {
			//add custom handling and exception handling
		}finally {
			driver.close();
		}
		return null;
	}

	
	@Override
	@Cacheable(value = "globalSearchCache", key = "'TargetStore'+#searchToken")
	public List<GlobalSearchItem> getGlobalSearchInfo(String searchToken) {
		
		ChromeDriver driver  = DriverFactory.getInstance().getDriver();
		
		try {
			String url = globalSearchUrl.concat(searchToken.trim());
			List<GlobalSearchItem> targetItems = new ArrayList<GlobalSearchItem>();
			
			int attempts = 0;
			driver.get(url);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			Long height = (Long) js.executeScript("return document.documentElement.scrollHeight");
			Integer tmp = 250;

			while (height > 0) {
				try{
					String scroller = String.format("window.scrollTo(0, %d)", tmp);
					((JavascriptExecutor) driver).executeScript(scroller);
					tmp += 250;
					height -= 250;
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
				products = driver.findElementsByXPath("//*[contains(@class, 'styles__StyledCardWrapper-sc-')]");
			}catch(Exception ex) {
				//add custom logging and exception handling
			}
			
			while (products.isEmpty() && attempts < 10) {
				products = driver.findElementsByXPath("//*[contains(@class, 'styles__StyledCardWrapper-sc-')]");
				attempts++;
			}

			attempts = 0;
			if (!products.isEmpty()) {
				products = driver.findElementsByXPath("//*[contains(@class, 'styles__StyledCardWrapper-sc-')]");
				while (products.get(products.size() / 2).getText().contains("Loading") && attempts < 10) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// Custom Logging and Exception Handling
					}
					attempts++;
					products = driver.findElementsByXPath("//*[contains(@class, 'styles__StyledCardWrapper-sc-')]");
				}
			}

			for (int i = 0; i < products.size(); i++) {
				
				WebElement e = products.get(i);
				
				GlobalSearchItem gsi = new GlobalSearchItem();
				gsi.setStore(store);
				//block to fetch product URL
				try {
					List<WebElement> productUrl = e.findElements(By.xpath(".//*[contains(@class, 'styles__StyledTitleLink-sc-')]"));
					gsi.setUrl(productUrl.get(0).getAttribute("href"));
				} catch (Exception ex) {
					// Custom Logging and Exception Handling
				}
				
				//block to fetch product Name
				try {
					List<WebElement> productName = e.findElements(By.xpath(".//*[contains(@class, 'styles__StyledTitleLink-sc-')]"));
					gsi.setProductName(productName.get(0).getText());
					for (WebElement w : productName) {
						gsi.setProductName(w.getText());
					}
				} catch (Exception ex) {
					// Custom Logging and Exception Handling	
				}

				//block to fetch Image url
				try {
					List<WebElement> imageUrls = e.findElements(By.xpath(".//*[contains(@class, 'ProductCardImage__PicturePrimary-sc-')]"));
					String rawUrl = imageUrls.get(0).getAttribute("innerHTML");
					String imageUrl = rawUrl.substring(rawUrl.indexOf("srcset="),rawUrl.indexOf("?qlt")).replace("srcset=\"", "");
					gsi.setImageUrl(imageUrl);
				} catch (Exception ex) {
					// Custom Logging and Exception Handling
					gsi.setImageUrl(BusinessConstants.NOT_AVAILABLE);
				}
					
				//block to fetch Rating
				try {
					List<WebElement> ratings = e.findElements(By.xpath(".//*[contains(@class, 'utils__ScreenReaderOnly-sc-')]"));
					if(ratings.get(0).getText().contains(".")) {
						gsi.setRating(ratings.get(0).getText().replace(" out of ", "").substring(0, 3).concat("/5"));
					}else {
						gsi.setRating(ratings.get(0).getText().replace(" out of ", "").substring(0, 1).concat("/5"));
					}
					
				} catch (Exception ex) {
					// Custom Logging and Exception Handling
					gsi.setRating(BusinessConstants.NOT_AVAILABLE);
				}
				
				//block to fetch Price
				try {
					List<WebElement> prices = e.findElements(By.xpath(".//*[contains(@class, 'styles__ProductCardPriceAndPromoStyled-sc-')]"));
					String price = prices.get(0).getAttribute("innerHTML");
					String productPrice = price.substring(price.indexOf("<span>"),price.indexOf("</span>")).replace("<span>", "");
					gsi.setPrice(productPrice);
				} catch (Exception ex) {
					// Custom Logging and Exception Handling
					gsi.setPrice(null);
				}
					
				if(gsi.isValidItem())
					targetItems.add(gsi);
			}
			if(!targetItems.isEmpty()) 
				return targetItems;
			else
				return null;
		}catch(Exception ex) {
			//add custom logging and Exception Handling
		}finally {
			driver.quit();
		}
		return null;
	}
	
}
