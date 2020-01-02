package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;

public class iPhonePriceCheckerPage extends BasePage {

	// Initializing the Page Objects:.......................
	public iPhonePriceCheckerPage() {
		PageFactory.initElements(driver, this);
	}

	// Objects Repository(OR):..............................
	@FindBy(css="input#twotabsearchtextbox")
	WebElement amazonSearchBox;
	
	@FindBy(xpath="//input[@placeholder='Search for products, brands and more']")
	WebElement flipkartSearchBox;
	
	@FindBy(xpath="(//button[contains(@class,'kmmA')])[1]")
	WebElement closeLoginForm;
	
	
	
	


	// Actions:.............................................
	public int verify_iPhone_XR_64GB_yellow_price_in_amazon(String searchItem) {
		amazonSearchBox.sendKeys(searchItem + Keys.ENTER);
		String price = driver.findElement(By.xpath("(//span[contains(text(),'"+searchItem+"')]//following::span[@class='a-price-whole'])[1]")).getText();
		int finalPrice = Integer.parseInt(price.replaceAll("[^0-9]", ""));
		return finalPrice;
	}

	public int verify_iPhone_XR_64GB_yellow_price_in_flipkart(String searchItem) throws Exception {
		closeLoginForm.click();
		flipkartSearchBox.sendKeys(searchItem + Keys.ENTER);
		String price = driver.findElement(By.xpath("//div[contains(text(),'iPhone XR (Yellow, 64 GB)')]//following::div[contains(@class,'_1uv')]//div[1]")).getText();
		int finalPrice = Integer.parseInt(price.replaceAll("[^0-9]", ""));
		return finalPrice;
	}
	
	
	

}
