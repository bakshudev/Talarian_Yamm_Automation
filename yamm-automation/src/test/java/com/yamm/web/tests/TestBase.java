package com.yamm.web.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Locator.ClickOptions;
import com.microsoft.playwright.options.*;
import com.yamm.web.pages.YammAppDialog;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestBase {

	// Define required shared objects and testConfig as static 
	// so that they can be initialized only once in beforeSuite()
	// and they can be shared/used across all test classes/cases
	static Playwright playwright;
	static Browser browser;

	// Define per test case objects as non static 
	// so that they get a new instance for each test method
	BrowserContext context;
	Page page;
	YammAppDialog yammFrame;
		
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

	}

	@AfterMethod
	void closeContext() throws InterruptedException {
		Thread.sleep(3000); // Static wait just to see final UI & state
		context.close();
	}

}