package com.yamm.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Locator.ClickOptions;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class Yamm_Email_Sending_Tests {
	
  public static void main(String[] args) throws InterruptedException {
	 
	  // Setup Chrome browser using Playwright
	  Playwright playwright = Playwright.create();
	  Browser browser = playwright.chromium().launch(
			  											new BrowserType.LaunchOptions()
			  												.setHeadless(false)
			  												.setChannel("chrome"));
	  // Create a new browser context and a new page
	  BrowserContext context = browser.newContext();;
	  Page page = context.newPage();
	  
	  // Login & open Goolge Sheets Home
     page.navigate("https://docs.google.com/spreadsheets/");
     page.locator("#identifierId").fill("bakshu.test@gmail.com");
     page.locator("button:has-text('Next')").first().click();
     page.locator("[name=password]").fill("YammTest#1");
     page.locator("button:has-text('Next')").first().click();
     
     // Open required Google Sheet File & Sheet
     page.waitForNavigation(() -> {
    	 page.locator("text=Discount Coupon Campaign").click();
    	 page.locator("span[class=\"docs-sheet-tab-name\"]:has-text(\"Sheet1\")").click();
     });
          
     // Open YAMM dialog
     page.locator("#docs-extensions-menu").click();
     page.waitForSelector("text=Yet Another Mail Merge: Mail Merge for Gmail►");
      // initially, it just displays help. We need this delay so that it loads all options and displays "Start Mail Merge"
     page.locator("text=Yet Another Mail Merge: Mail Merge for Gmail►").click(new ClickOptions().setDelay(2000));
     page.locator("text=Start Mail Merge").click();
 	 FrameLocator yammFrame = page.frameLocator("[src*=\"macros\"]").frameLocator("#sandboxFrame").frameLocator("#userHtmlFrame");
     
 	 // Perform required actions in YAMM dialog
 	 yammFrame.locator("#senderName_input").fill("EMAIL SENDER");
     yammFrame.locator("#drafts_list").selectOption("r5543221579514015015");
     yammFrame.locator("#readReceiptCheckbox").uncheck();
     Thread.sleep(3000);
     yammFrame.locator("#sendButton").click(); // Just comment sending emails for now

     // Print messages after sending emails and close YAMM
     Thread.sleep(3000);
     yammFrame.locator("#mainPanel button:has-text('Close')").focus();
     System.out.println(yammFrame.locator("#contextualAlert").innerText());
     System.out.println(yammFrame.locator("#mainPanel div:nth-child(1)").innerText());
     yammFrame.locator("#mainPanel button:has-text('Close')").click();
   

  }
}