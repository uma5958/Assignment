package com.testcases;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.pages.iPhonePriceCheckerPage;



public class iPhonePriceCheckerTest extends BasePage {
	iPhonePriceCheckerPage priceCheckerPage;
	
	@BeforeMethod
	public void setUp() {
		initialization();
		priceCheckerPage = new iPhonePriceCheckerPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verify_iPhone_XR_64GB_yellow_price_Test() throws Exception {
		driver.get(prop.getProperty("amazon"));
		int amazonPrice = priceCheckerPage.verify_iPhone_XR_64GB_yellow_price_in_amazon(prop.getProperty("searchItem"));
		driver.navigate().to(prop.getProperty("flipkart"));
		int flipKartPrice = priceCheckerPage.verify_iPhone_XR_64GB_yellow_price_in_flipkart(prop.getProperty("searchItem"));
		if (amazonPrice < flipKartPrice) {
			Reporter.log(prop.getProperty("searchItem")+" Price Result: ", true);
			Reporter.log("Amazon price("+amazonPrice+") is less than Flipkart price("+flipKartPrice+")", true);
		} else if(flipKartPrice < amazonPrice){
			Reporter.log("Flipkart price("+flipKartPrice+") is less than Amazon price("+amazonPrice+")", true);
		} else {
			Reporter.log("Amazon and Flipkart prices are equal",true);
		}
		
	}
	
	
	
}
