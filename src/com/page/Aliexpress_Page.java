package com.page;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pageObject.PageObject;

public class Aliexpress_Page extends PageObject {
	
	private static WebDriver driver = null;
	String file = "test";
	Properties prop = this.readPropertiesFileForTest(file);
	
	//These are the web element repository
	By searchBox = By.xpath("//*[@id='search-key']");
	By newToAliexpress = By.xpath("//a[@aria-label='关闭']");
	By pageN = By.xpath("//button[@type='button']");
	By buttonPage = By.xpath("//button[@aria-label='Page 2, 7 pages']");
	By elementM = By.xpath("//a[@target='_blank']");
	By item = By.xpath("//span[@class='']");
	
	//From here, methods
	public WebDriver openAliExpress() {
		System.out.println("launching Chrome browser");
		Properties prop = super.properties();
		String test1_URL = prop.getProperty("test1_URL");
		try {
			driver = super.openURL(test1_URL);
		} catch (Exception e) {
			System.out.println(e);
		}
		return driver;	
	}
	
	public Properties readPropertiesFileForTest(String file) {
		Properties prop = new Properties();
		try {
		    prop.load(new FileInputStream("resources\\config."+file));
		} 
		catch (IOException ex) {
		    ex.printStackTrace();
		}
		return prop;
	}
	
	public void lookForItem() {
		//Properties prop = this.readPropertiesFileForTest(file);
		String itemName = prop.getProperty("itemName");
		
//		2 - Look for Iphone
		//System.out.println(driver.findElement(searchBox));
		driver.findElement(searchBox).sendKeys(itemName);
		driver.findElement(searchBox).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
		// This is to close the NEW TO ALIEXPRESS pop up
		driver.findElement(newToAliexpress).click();
	}
	
	public void goToPageN() {
		//3 - Go to page N
		String page = prop.getProperty("page");
		List<WebElement> lista = new ArrayList<WebElement>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int encontro = 0;
		int i=0;
		while(encontro == 0 && i < 100) {
			System.gc(); 
			lista = driver.findElements(pageN);

			for (WebElement webElement : lista) {
				if(webElement.getText().equals(page)) {
					encontro++;
					System.out.println("Page " + page + " was found!");
					try {
						driver.findElement(buttonPage).click();
					} catch (Exception e) {
						System.out.println(e);
					}
				}	
				//System.out.println(webElement.getText().trim());
			}
			//System.out.println("--- Found it : " + encontro);
			if(encontro == 0) //scroll down
				js.executeScript("window.scrollBy(0,500)", "");
		}
	
	}
	
	
	public void lookForElementM() {
//		4 - Look for M element
		List<WebElement> lista = new ArrayList<WebElement>();
		lista = driver.findElements(elementM);
		int n = Integer.parseInt(prop.getProperty("item"));
		String href = lista.get(n-1).getAttribute("href");
		//System.out.println(href);
		driver.get(href);
	}
	
	public int verifyAvailability() {
//		5 - Verify that there is availability
		int available = 0;
		List<WebElement> lista = new ArrayList<WebElement>();
		lista = driver.findElements(item);
		for (WebElement webElement : lista) {
			if(webElement.getText().contains("pieces available")) {
				int aux = webElement.getText().length(); 
//				System.out.println(webElement.getText());			
				String number = (String) webElement.getText().subSequence(0, aux - 17);
//				System.out.println(number);
				available = Integer.parseInt(number);
				System.out.println("There are " + available + " available items");
				return available;
			}
		}
		return available;
	}

}
