package com.price.comparision.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
public class AmazonInterfaceImpl implements AmazonInterface {

	@Value("${amazon.url}")
	private String uri;

	@Value("${amazon.store}")
	private String store;

	@Value("${amazon.retry}")
	private Integer retryAttempts;

	@Value("${amazon.global.search.url}")
	private String globalSearchUrl;

	@Value("${amazon.global.search.base.uri}")
	private String baseUri;

	@Override
	@Cacheable(value = "localSearchCache", key = "'AmazonStore'+#id")
	public ItemDetail getItemInfo(String id, int retry) {

		String url = uri + id;

		ItemDetail detail = new ItemDetail();

		if (id.equals(DatabaseConstants.NOT_AVIALABLE)) {
			detail.setStoreName(store);
		} else {
			final WebClient client = new WebClient(BrowserVersion.FIREFOX);
			client.getOptions().setCssEnabled(false);
			client.getOptions().setUseInsecureSSL(true);
			client.getOptions().setJavaScriptEnabled(false);
			client.getOptions().setLocalAddress(null);
			client.getOptions().getLocalAddress();
			detail.setStoreName(store);
			detail.setUrl(url);

			try {

				HtmlPage page = client.getPage(url);

				List<HtmlElement> list = page.getByXPath("//*[contains(@class, 'priceToPay')]");
				
				if(list.isEmpty())
				    list = page.getByXPath("//*[contains(@class, 'apexPriceToPay')]");
				for (HtmlElement h : list) {
					detail.setPrice(h.getFirstChild().getTextContent());
				}

				if (detail.getRating() == BusinessConstants.NOT_AVAILABLE) {
					list = page.getByXPath("//*[contains(@class, 'a-icon-alt')]");
					if (list.size() > 0) {
						detail.setRating(list.get(0).getTextContent().replace(" out of 5 stars", "/5"));
					}
				}

				if (detail.getPrice() != BusinessConstants.NOT_AVAILABLE) {
					return detail;
				} else if (retry < retryAttempts) {
					return getItemInfo(id, ++retry);
				}

			} catch (Exception e) {
				// add Custom logger here
			} finally {
				client.close();
			}
		}

		return detail;
	}

	@Override
	@Cacheable(value = "globalSearchCache", key = "'AmazonStore'+#searchToken")
	public List<GlobalSearchItem> getGlobalSearchInfo(String searchToken) {
		
		ChromeDriver driver = DriverFactory.getInstance().getDriver();
		
		try {
			String url = globalSearchUrl.concat(searchToken.replaceAll(" ", "+").trim());

			List<GlobalSearchItem> amazonItems = new ArrayList<GlobalSearchItem>();

			int attempts = 0;
			driver.get(url);

			List<WebElement> products = driver.findElementsByXPath("//*[contains(@class, 'search-results_')]");

			while (products.isEmpty() && attempts < 10) {
				products = driver.findElementsByXPath("//*[contains(@class, 'search-results_')]");
				attempts++;
			}

			for (int i = 0; i < products.size(); i++) {

				GlobalSearchItem gsi = new GlobalSearchItem();
				gsi.setStore(store);

				WebElement e = products.get(i);

				try {
					String productName = e.findElement(By.className("a-link-normal")).getAttribute("innerText");
					gsi.setProductName(productName);
				} catch (Exception ex) {
					// add Custom Logging and exception handling here
				}

				if (gsi.getProductName() == null || gsi.getProductName() == "") {
					try {
						WebElement name = e.findElement(By.className("a-size-base-plus"));
						gsi.setProductName(name.getText());
					} catch (Exception ex) {
						// add Custom Logging and exception handling here
					}
				}

				if (gsi.getProductName() == null || gsi.getProductName() == "") {
					try {
						WebElement name = e.findElement(By.className("a-size-medium"));
						gsi.setProductName(name.getText());
					} catch (Exception ex) {
						// add Custom Logging and exception handling here
					}
				}

				try {
					String productUrl = e.findElement(By.className("a-link-normal")).getAttribute("href");
					gsi.setUrl(productUrl);
				} catch (Exception ex) {
					// add Custom Logging and exception handling here
				}
				
				try {
					List<WebElement> price = e.findElements(By.className("a-offscreen")); // e.findElement(By.className("a-offscreen"));
					while (price.isEmpty() && attempts < 10) {
						price = e.findElements(By.className("a-offscreen"));
						attempts++;
					}
					if (!price.isEmpty())
						gsi.setPrice(price.get(0).getAttribute("innerText"));
				}catch(Exception ex){
					//add Custom Logging and Exception Handling
				}
				

				try {
					attempts = 0;
					List<WebElement> rating = e.findElements(By.className("a-icon-alt")); // e.findElement(By.className("a-offscreen"));
					while (rating.isEmpty() && attempts < 10) {
						rating = e.findElements(By.className("a-icon-alt"));
						attempts++;
					}
					if (!rating.isEmpty())
						gsi.setRating(rating.get(0).getAttribute("innerText").replace(" out of 5 stars", "/5"));
					else
						gsi.setRating(BusinessConstants.NOT_AVAILABLE);
				}catch(Exception ex) {
					//add custom Logging and Exception handling
				}

				try {
					String imageUrl = e.findElement(By.className("s-image")).getAttribute("src");
					gsi.setImageUrl(imageUrl);
				} catch (Exception ex) {
					// add Custom Logging and exception handling here
				}

				if (gsi.isValidItem())
					amazonItems.add(gsi);

			}			
			return amazonItems;
		}catch(Exception Ex) {
			//add customer logging and exception
		}finally {
			driver.close();
		}
		return null;
	}
}
