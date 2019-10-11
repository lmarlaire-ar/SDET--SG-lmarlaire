package com.pageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {
	
	/*
	 * Author: 	lmarlaire@gmail.com
	 * Date: 	11/10/2019
	 * 
	 */

	private WebDriver driver = null;
	private static String webdriver_path; // = "D:\\\\apps\\\\Selenium\\\\webdrivers\\\\chromedriver_win32\\\\chromedriver.exe";

	
	 public Properties properties() {
		 
		System.out.println("Hello World Deviget! This is Lucas Marlaire");
		
		// Instance properties file config.properties to read 
		Properties prop = new Properties();
		try {
		    prop.load(new FileInputStream("resources\\config.properties"));
		} 
		catch (IOException ex) {
		    ex.printStackTrace();
		}
	
		// Set the web driver path regarding the local machine configuration
		webdriver_path = prop.getProperty("webdriver_path");
		return prop;
	 }
	 
	 public void scrollDown(WebDriver driver) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,450)", "");
	 }
	 
	 public WebDriver openURL(String url) {
		// 1 - Open url in browser
			System.setProperty("webdriver.chrome.driver", webdriver_path);
			this.driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(url);
			System.out.println(driver.getTitle() + " is opening");
			
			return driver;
	 }

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	 
	
}
