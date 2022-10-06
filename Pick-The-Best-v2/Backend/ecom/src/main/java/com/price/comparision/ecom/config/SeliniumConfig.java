package com.price.comparision.ecom.config;

import java.util.logging.Level;

import javax.annotation.PostConstruct;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeliniumConfig {
	
	@PostConstruct
	void PostConstruct() {
		System.setProperty("webdriver.chrome.driver", "C:\\ECom\\Pick-The-Best-v2\\resources\\new\\chromedriver.exe");
		System.out.println("Started"+System.getProperty("webdriver.chrome.driver"));	
		}
	
	@Bean
	public ChromeDriver driver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36";
		options.addArguments("user-agent="+userAgent);
		options.addArguments("--log-level=OFF");
		options.addArguments("--silent");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); 
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		return new ChromeDriver(options);
		
	}
	
}
