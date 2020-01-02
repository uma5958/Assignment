package com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.base.BasePage;

public class TripAdviserPage extends BasePage {

	// Initializing the Page Objects:.......................
	public TripAdviserPage() {
		PageFactory.initElements(driver, this);
	}

	// Objects Repository(OR):..............................
	@FindBy(xpath="//div[@title='Search']")
	WebElement searchBox;

	@FindBy(xpath="//input[contains(@id,'mainSearch')]")
	WebElement mainSearchBox;

	@FindBy(xpath="(//div[@class='result-title'])[1]")
	WebElement firstResult;

	@FindBy(xpath="//a[contains(.,'Write a review')]")
	WebElement writeReviewBtn;

	@FindBy(xpath="(//div[@data-error='RATE_OVERALL']//div/span)[1]")
	WebElement overallRatingBtn;

	@FindBy(xpath="//em[text()='Excellent']")
	WebElement fiveStar_Excelent;

	@FindBy(xpath = "//input[contains(@id,'ReviewTitle')]")
	WebElement titleOfYourReviewTextBox;

	@FindBy(xpath = "//textarea[contains(@id,'ReviewText')]")
	WebElement yourReviewTextBox;

	@FindBy(xpath="//div[text()='Hotel Ratings']")
	WebElement hotelRatingsSection;

	@FindBy(xpath="//div[text()='Service']/..//div/span")
	WebElement serviceRatingBtn;

	@FindBy(xpath="//div[text()='Service']//following::div[text()='Excellent']")
	WebElement hotel_Service_FiveStar_Excelent;
	
	@FindBy(xpath = "//input[@id='noFraud']")
	WebElement submitYourReviewCheckBox;









	// Actions:.............................................
	public void verify_Write_a_review_for_the_given_input(String searchItem, String titleOfYourReview, String yourReview) throws Exception {
		searchBox.click();
		mainSearchBox.sendKeys(searchItem + Keys.ENTER);
		firstResult.click();
		switchToNewWindow(driver, 1);
		scrollIntoView(driver, writeReviewBtn);
		jsClick(driver, writeReviewBtn);
		switchToNewWindow(driver, 2);
		new Actions(driver).moveToElement(overallRatingBtn, 50, 0).click().build().perform();
		Assert.assertTrue(fiveStar_Excelent.isDisplayed(), "Five star rating not given");
		Reporter.log("5 star rating given successfully");
		titleOfYourReviewTextBox.sendKeys(titleOfYourReview + Keys.TAB);
		Reporter.log("Title of your review given successfully", true);
		for (int i = 0; i < 3; i++) {
			yourReviewTextBox.sendKeys(yourReview + Keys.TAB);
		}
		Reporter.log("Your review given successfully", true);
		// Hotel Ratings Section
		scrollIntoView(driver, hotelRatingsSection);
		new Actions(driver).moveToElement(serviceRatingBtn, 50, 0).click().build().perform();
		Assert.assertTrue(hotel_Service_FiveStar_Excelent.isDisplayed(), "Five star rating not given for hotel service");
		scrollIntoView(driver, submitYourReviewCheckBox);
		submitYourReviewCheckBox.click();
		Reporter.log("Checkbox checked successfully", true);
	}





}
