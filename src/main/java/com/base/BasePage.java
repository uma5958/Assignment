/**
 * @author UmaMaheswararao
 */

package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.Listeners.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {	

	public static WebDriver driver;

	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;


	public BasePage(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization(){
		String browserName = prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); 
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(); 
		} 
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	

	public WebDriver getDriver() {
		WebDriver webDriver = driver;
		return webDriver;
	}
	
	
	//------------------ Action Methods ------------------------------------------
	public static void waitForElementPresent(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			Reporter.log("Some exception/error occured while waiting for element: "+locator.toString(), true);
			e.printStackTrace();
		}
	}
	
	public static <T> void scrollIntoView(WebDriver driver, T elementAttr) {
		if(elementAttr.getClass().getName().contains("By")) {
			waitForElementPresent(driver, (By) elementAttr);
			WebElement ele = driver.findElement((By)elementAttr);
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
		}
	}
	
	public static void switchToNewWindow(WebDriver driver, int no) {
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(browserTabs .get(no));
	}
	
	public static <T> void jsClick(WebDriver driver, T elementAttr){
		if(elementAttr.getClass().getName().contains("By")) {
			waitForElementPresent(driver, (By) elementAttr);
			WebElement ele = driver.findElement((By)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		}
	}
	
	public static <T> void jsType(WebDriver driver, T elementAttr, String value){
		if(elementAttr.getClass().getName().contains("By")) {
			waitForElementPresent(driver, (By) elementAttr);
			WebElement ele = driver.findElement((By)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'", ele);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'", ele);
		}
	}
	
	
	
	
	
	
}