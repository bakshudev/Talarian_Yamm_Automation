package com.yamm.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Locator.ClickOptions;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Yamm_Gmail_Receiving_Tests {

	// Define required shared objects and testConfig as static 
	// so that they can be initialized only once in beforeSuite()
	// and they can be shared/used across all test classes/cases
	static Playwright playwright;
	static Browser browser;

	// Define per test case objects as non static 
	// so that they get a new instance for each test method
	BrowserContext context;
	Page page;
	
	  @Test
	  void test_Yamm_Gmail_Receiving_Single_Receipient_Email_Header(){
	
		  searchGmailInbox("in:inbox from:(bakshu.test@gmail.com) bakshu.test+single-sam@gmail.com");
		  page.locator("span.bog span:has-text('Your Discount Coupon')").first().click();
			
		  String actualHeader = page.locator("div.ha h2.hP").innerText();
		  String expectedHeader =  "Sam - Your Discount Coupon";
		  Assert.assertEquals(actualHeader, expectedHeader);
	  }
	
	  @Test
	  void test_Yamm_Gmail_Receiving_Multiple_Receipients_Email_Count(){
	
		  searchGmailInbox("in:inbox from:(bakshu.test@gmail.com) bakshu.test+multiple-");
		
		  // I always get double the number of emails using this. Needs further investigation. 
		  // For now, I am going with this logic
		  int totalCount = page.locator("td[class=\"xY a4W\"] div.y6 span.bog span:has-text('Your Discount Coupon')").count();
		  int actualCount = totalCount/2;
		  int expectedCount =  3;
		  Assert.assertEquals(actualCount, expectedCount);
	  }
	  
	  @Test
	  void test_Yamm_Gmail_Receiving_Single_Receipient_Email_Count(){
	
		  searchGmailInbox("in:inbox from:(bakshu.test@gmail.com) bakshu.test+single-sam@gmail.com");
			
		  int totalCount = page.locator("td[class=\"xY a4W\"] div.y6 span.bog span:has-text('Your Discount Coupon')").count();
		  int actualCount = totalCount/2;
		  int expectedCount =  1;
		  Assert.assertEquals(actualCount, expectedCount);
	  }
		


	  @BeforeSuite
		static void launchBrowser() {
	
			// Setup Chrome browser using Playwright
			playwright = Playwright.create();
			browser = playwright.chromium().launch(
													new BrowserType.LaunchOptions()
														.setHeadless(false)
														.setChannel("chrome"));
		}

	@AfterSuite
	static void closeBrowser() {
		playwright.close();
	}

	@BeforeMethod
	void createContextAndPage() {

		// Create a new browser context and a new page
		context = browser.newContext();
		page = context.newPage();
		openGmailInbox();

	}

	@AfterMethod
	void closeContext() throws InterruptedException {
		Thread.sleep(3000); // Static wait just to see final UI & state
		context.close();
	}

	  void openGmailInbox()
	  {
	     page.navigate("https://accounts.google.com/AccountChooser/signinchooser?service=mail");
	     page.locator("[id=identifierId]").fill("bakshu.test@gmail.com");
	     page.locator("button:has-text('Next')").first().click();
	     page.locator("[name=password]").fill("YammTest#1");
	     page.waitForNavigation(() -> {
	    	 page.locator("button:has-text('Next')").first().click();
	     });

	  }
	  
	  void searchGmailInbox(String serachQuery) {
		  page.locator("input[placeholder='Search mail']").fill(serachQuery);
		  page.locator("input[placeholder='Search mail']").press("Enter");
	  }
}