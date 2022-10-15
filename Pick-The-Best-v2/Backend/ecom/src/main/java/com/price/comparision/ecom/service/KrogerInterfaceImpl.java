package com.price.comparision.ecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.price.comparision.ecom.config.DriverFactory;
import com.price.comparision.ecom.constants.BusinessConstants;
import com.price.comparision.ecom.constants.DatabaseConstants;
import com.price.comparision.ecom.model.GlobalSearchItem;
import com.price.comparision.ecom.model.ItemDetail;

@Service
public class KrogerInterfaceImpl implements KrogerInterface {
	
	@Value("${kroger.url}")
	private String uri;

	@Value("${kroger.store}")
	private String store;
	
	@Value("${kroger.retry}")
	private Integer retryAttempts;
	
	@Value("${kroger.global.search.url}")
	private String globalSearchUrl;

	@Override
	@Cacheable(value="localSearchCache", key = "'KrogerStore'+#id")
	public ItemDetail getItemInfo(String id, int retry) {
		
		String url = uri.replace("replaceIdHere", id);
		
		ItemDetail detail= new ItemDetail();
		
		if(id.equals(DatabaseConstants.NOT_AVIALABLE)) {
			detail.setStoreName(store);
		}else {
			final WebClient client = new WebClient(BrowserVersion.FIREFOX);
			client.getOptions().setCssEnabled(false);
			client.getOptions().setUseInsecureSSL(true);
			client.getOptions().setJavaScriptEnabled(false);
			client.getOptions().setLocalAddress(null);

			detail.setStoreName(store);
			detail.setUrl(url);
			
			try {

				HtmlPage page =  client.getPage(url);
				
				List<HtmlElement> list = page.getByXPath("//*[contains(@class, 'kds-Price')]");
				
				for(HtmlElement h: list) {
					if(h.toString().contains("cart-page-item-unit-price")) {
						detail.setPrice(h.getTextContent());
					}
				}
				
				if(detail.getPrice() != BusinessConstants.NOT_AVAILABLE) {
					return detail;
				}else if(retry <= retryAttempts){
					return getItemInfo(id,++retry);
				}
			} catch (Exception e) {
				detail.setPrice(BusinessConstants.NOT_AVAILABLE);
			} finally {
				client.close();
			}
		}
		
		return detail;
	}

	@Override
	@Cacheable(value = "globalSearchCache", key = "'KrogerStore'+#searchToken")
	public List<GlobalSearchItem> getGlobalSearchInfo(String searchToken) {
		
		ChromeDriver driver = DriverFactory.getInstance().getDriver();
		
		try{
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			String url = globalSearchUrl.replace("replaceTokenHere",searchToken.trim());
			List<GlobalSearchItem> krogerItems = new ArrayList<GlobalSearchItem>();
					
			int attempts = 0;
			try {
				driver.get(url);
			}catch(Exception ex) {
				//add custom logging and exception Handling
			}
			
			List<WebElement> products = new ArrayList<WebElement>();
			try {
				products = driver.findElementsByXPath("//*[contains(@class, 'ProductCard')]");
			}catch(Exception ex) {
				//add custom logging and exception handling
			}
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Long height = (Long) js.executeScript("return document.documentElement.scrollHeight");
			Integer tmp = 200;
			while (height > 0) {
				String scroller = String.format("window.scrollTo(0, %d)", tmp);
				((JavascriptExecutor) driver).executeScript(scroller);
				tmp += 200;
				height -= 200;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// Custom Logging and Exception Handling
				}
			}
		
			for(int i=0; i <products.size(); i++) {			
				GlobalSearchItem gsi = new GlobalSearchItem();
				gsi.setStore(store);		
				WebElement e = products.get(i);
				try {
					WebElement name = e.findElement(By.className("kds-Text--l"));
					gsi.setProductName(name.getAttribute("innerText"));
				}catch(Exception ex) {
					//add Custom Logging and exception handling here
				}
				
				if(gsi.getProductName() == null) {
					try {
						String productName= e.findElement(By.className("kds-Link")).getText();
						gsi.setProductName(productName);
					}catch(Exception ex) {
						//add Custom Logging and exception handling here
					}
				}
				
				try {
					String productUrl = e.findElement(By.className("kds-Link")).getAttribute("href");
					gsi.setUrl(productUrl);
				}catch(Exception ex) {
					//add Custom Logging and exception handling here
				}
				
				attempts = 0;	
				List<WebElement> price =   e.findElements(By.className("kds-Price"));  //e.findElement(By.className("a-offscreen"));
				while(price.isEmpty() && attempts < 10) {
					price = e.findElements(By.className("kds-Price"));
					attempts++;
				}
				if(!price.isEmpty())
					gsi.setPrice(price.get(0).getAttribute("innerText").toString().replaceAll("\n", ""));
				
				gsi.setRating(BusinessConstants.NOT_AVAILABLE);
			
				try {
					String imageUrl = e.findElement(By.className("kds-Image-img")).getAttribute("src");
					gsi.setImageUrl(imageUrl);
				}catch(Exception ex) {
					//add Custom Logging and exception handling here
				}
				
				if(gsi.isValidItem())
					krogerItems.add(gsi);	
				
			}
			
			if(!krogerItems.isEmpty()) 
				return krogerItems;
			else
				return null;
		}catch(Exception ex) {
			//add custom logging and Exceptional Handling
		}finally {
			driver.quit();
		}
		return null;	
	}
}
