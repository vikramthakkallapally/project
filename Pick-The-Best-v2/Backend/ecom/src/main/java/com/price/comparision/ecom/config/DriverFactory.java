package com.price.comparision.ecom.config;
import java.util.logging.Level;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory
{
	
	
   private DriverFactory() {
	   
   }
   public ChromeDriver getDriver() {
	   ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36";
			options.addArguments("user-agent="+userAgent);
			options.addArguments("--log-level=OFF");
			options.addArguments("--silent");
			java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); 
		return new ChromeDriver(options); // can be replaced with other browser drivers
   }
   
   public static DriverFactory getInstance() {
	   return new DriverFactory();
   }
   
}