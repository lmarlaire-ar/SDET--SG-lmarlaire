package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.page.Aliexpress_Page;
import com.pageObject.PageObject;

public class Aliexpress_Test extends PageObject {

	public WebDriver driver = null;
	Aliexpress_Page ali = new Aliexpress_Page();
	PageObject page = new PageObject();
	
	String itemName = "Iphone";
	
	/*
	 * Author: lmarlaire@gmail.com
	 * Create a automation project
		1 - Open aliexpress in browser
		2 - Look for Iphone
		3 - Go to page N
		4 - Look for M element
		5 - Verify that there is availability
		
		NOTE: Please, complete the resources\config.properties file
	 */

	@Test(priority = 0)
	public void launchPage() {
//		1 - Open aliexpress in browser 
	    ali.openAliExpress();
	    Assert.assertTrue(true);
	}
	
	@Test(priority = 1)
	public void lookForItem(){
//		2 - Look for Iphone
		ali.lookForItem();
		Assert.assertTrue(true);
	}

	@Test(priority = 2)
	public void goToPageN(){
//		3 - Go to page N
		ali.goToPageN();
		Assert.assertTrue(true);
	}
	
	@Test(priority = 3)
	public void lookForElementM(){
		//4 - Look for M element
		ali.lookForElementM();
		Assert.assertTrue(true);
	}

	@Test(priority = 4)
	public void verifyAvailability(){
//	5 - Verify that there is availability
		int availability = ali.verifyAvailability(); 
		if(ali.verifyAvailability()>0) {
			Assert.assertTrue(true, "VALIDATION PASSED: There are \"" + availability + "\" available items");
		}else {
			Assert.assertTrue(false, "VALIDATION FAILED: There are no availability for this product");
		}
	}
	
//	  @AfterTest
//	  public void closeWebBrowser() {
//		  ali.getDriver().close();
//	  }
}
