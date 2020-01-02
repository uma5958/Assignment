package com.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.pages.TripAdviserPage;



public class TripAdviserTest extends BasePage {
	TripAdviserPage tripAdviserPage;
	
	@BeforeMethod
	public void setUp() {
		initialization();
		tripAdviserPage = new TripAdviserPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verify_Write_a_review_for_the_given_input_Test() throws Exception {
		driver.get(prop.getProperty("tripAdviser"));
		tripAdviserPage.verify_Write_a_review_for_the_given_input(prop.getProperty("searchInput"), 
				prop.getProperty("titleOfYourReview"), prop.getProperty("finalReview"));
		
	}
	
	
	
}
