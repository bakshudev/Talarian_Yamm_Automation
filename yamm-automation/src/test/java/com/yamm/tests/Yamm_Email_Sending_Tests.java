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

public class Yamm_Email_Sending_Tests extends TestBase {

		
	  @Test
	  void test_Yamm_Email_Sending_To_Multiple_Receipients_Sent_Count_Message() {
	
			openGoogleSheet("Discount Coupon Campaign", "Multiple_Receipients");
			openYammApp();
			performYammAppActions();
			
			String actualCount = yammFrame.locator("#mainPanel div:nth-child(1)").innerText();
			String expectedCount = "3 emails sent.";
			yammFrame.locator("#mainPanel button:has-text('Close')").click();
			
			Assert.assertEquals(actualCount, expectedCount);
	  }
	
	  @Test
	  void test_Yamm_Email_Sending_To_Single_Receipient_Sent_Count_Message() {
	
			openGoogleSheet("Discount Coupon Campaign", "Single_Receipient");
			openYammApp();
			performYammAppActions();
			
			String actualCount = yammFrame.locator("#mainPanel div:nth-child(1)").innerText();
			String expectedCount = "1 emails sent.";
			yammFrame.locator("#mainPanel button:has-text('Close')").click();
			
			Assert.assertEquals(actualCount, expectedCount);
	  }
	  
	  @Test
	  void test_Yamm_Email_Sending_To_No_Receipient_Alert_Message() {
	
			openGoogleSheet("Discount Coupon Campaign", "No_Receipient");
			openYammApp();
			
			String actualMsg = yammFrame.locator("div.yamm__alert_message_container").innerText();
			String expectedMsg = "There are no recipients in your sheet";
			
			Assert.assertEquals(actualMsg, expectedMsg);
	  }

	
	private void openGoogleSheet(String fileName, String sheetName) {
		// Login & open Goolge Sheets Home
		page.navigate("https://docs.google.com/spreadsheets/");
		page.locator("#identifierId").fill("bakshu.test@gmail.com");
		page.locator("button:has-text('Next')").first().click();
		page.locator("[name=password]").fill("YammTest#1");
		page.locator("button:has-text('Next')").first().click();

		// Open required Google Sheet File & Sheet
		page.waitForNavigation(() -> {
			page.locator("text=" + fileName).click();
			page.locator("span[class=\"docs-sheet-tab-name\"]:has-text(\"" + sheetName + "\")").click();
		});
	}

	private void openYammApp() {
		// Open YAMM dialog
		page.locator("#docs-extensions-menu").click();
		page.waitForSelector("text=Yet Another Mail Merge: Mail Merge for Gmail►");
		// initially, it just displays help. We need this delay so that it loads all
		// options and displays "Start Mail Merge"
		page.locator("text=Yet Another Mail Merge: Mail Merge for Gmail►").click(new ClickOptions().setDelay(2000));
		page.locator("text=Start Mail Merge").click();
		
		yammFrame = page.frameLocator("[src*=\"macros\"]").frameLocator("#sandboxFrame").frameLocator("#userHtmlFrame");
	}
	
	private void performYammAppActions()
	{
		yammFrame.locator("#senderName_input").fill("Bakshu");
		yammFrame.locator("#drafts_list").selectOption("r5543221579514015015");
		yammFrame.locator("#readReceiptCheckbox").uncheck();
		yammFrame.locator("#sendButton").click();
		yammFrame.locator("#mainPanel button:has-text('Close')").focus(); // Wait for the close button to appear	
	}
}