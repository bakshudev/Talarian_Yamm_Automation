package com.yamm.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class Yamm_Email_Sending_Tests {
	
  public static void main(String[] args) {
	  
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext context = browser.newContext();

      // Open new page
      Page page = context.newPage();

      // Go to https://accounts.google.com/ServiceLogin?service=wise&passive=1209600&continue=https://docs.google.com/spreadsheets/&followup=https://docs.google.com/spreadsheets/&ltmpl=sheets
      page.navigate("https://accounts.google.com/ServiceLogin?service=wise&passive=1209600&continue=https://docs.google.com/spreadsheets/&followup=https://docs.google.com/spreadsheets/&ltmpl=sheets");

      // Go to https://accounts.google.com/signin/v2/identifier?service=wise&passive=1209600&continue=https%3A%2F%2Fdocs.google.com%2Fspreadsheets%2F&followup=https%3A%2F%2Fdocs.google.com%2Fspreadsheets%2F&ltmpl=sheets&flowName=GlifWebSignIn&flowEntry=ServiceLogin
      page.navigate("https://accounts.google.com/signin/v2/identifier?service=wise&passive=1209600&continue=https%3A%2F%2Fdocs.google.com%2Fspreadsheets%2F&followup=https%3A%2F%2Fdocs.google.com%2Fspreadsheets%2F&ltmpl=sheets&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

      // Click [aria-label="Email or phone"]
      page.locator("[aria-label=\"Email or phone\"]").click();

      // Fill [aria-label="Email or phone"]
      page.locator("[aria-label=\"Email or phone\"]").fill("bakshu.test@gmail.com");

      // Click button:has-text("Next")
      // page.waitForNavigation(new Page.WaitForNavigationOptions().setUrl("https://accounts.google.com/signin/v2/challenge/pwd?service=wise&passive=1209600&continue=https%3A%2F%2Fdocs.google.com%2Fspreadsheets%2F&followup=https%3A%2F%2Fdocs.google.com%2Fspreadsheets%2F&ltmpl=sheets&flowName=GlifWebSignIn&flowEntry=ServiceLogin&cid=1&navigationDirection=forward&TL=AM3QAYYEgYw-MVP1Yt7oXUBty9Jq1o9MpfPpb5opFykx32tfKsP3PWxUjYiox9Ce"), () ->
      page.waitForNavigation(() -> {
        page.locator("button:has-text(\"Next\")").click();
      });

      // Click [aria-label="Enter your password"]
      page.locator("[aria-label=\"Enter your password\"]").click();

      // Fill [aria-label="Enter your password"]
      page.locator("[aria-label=\"Enter your password\"]").fill("YammTest#1");

      // Click button:has-text("Next")
      // page.waitForNavigation(new Page.WaitForNavigationOptions().setUrl("https://docs.google.com/spreadsheets/u/0/"), () ->
      page.waitForNavigation(() -> {
        page.locator("button:has-text(\"Next\")").click();
      });

      // Click text=Discount Coupon Campaign
      // page.waitForNavigation(new Page.WaitForNavigationOptions().setUrl("https://docs.google.com/spreadsheets/d/1Rx921mthomVCMzsTMNUldHTOuQDC_4L0_1bBfaVCzm0/edit#gid=0"), () ->
      page.waitForNavigation(() -> {
        page.locator("text=Discount Coupon Campaign").click();
      });
      // assertThat(page).hasURL("https://docs.google.com/spreadsheets/d/1Rx921mthomVCMzsTMNUldHTOuQDC_4L0_1bBfaVCzm0/edit?ouid=105947502186538054955&usp=sheets_home&ths=true");

      // Click text=Extensions
      page.locator("text=Extensions").click();

      // Click div[role="menuitem"]:has-text("Yet Another Mail Merge: Mail Merge for Gmail►")
      page.locator("div[role=\"menuitem\"]:has-text(\"Yet Another Mail Merge: Mail Merge for Gmail►\")").click();

      // Click text=Start Mail Merge
      page.locator("text=Start Mail Merge").click();

      // Click [placeholder="Your name"]
      page.frame("userHtmlFrame").locator("[placeholder=\"Your name\"]").click();

      // Click [placeholder="Your name"]
      page.frame("userHtmlFrame").locator("[placeholder=\"Your name\"]").click();

      // Double click [placeholder="Your name"]
      page.frame("userHtmlFrame").locator("[placeholder=\"Your name\"]").dblclick();

      // Fill [placeholder="Your name"]
      page.frame("userHtmlFrame").locator("[placeholder=\"Your name\"]").fill("Bakshu");

      // Check #readReceiptCheckbox
      page.frame("userHtmlFrame").locator("#readReceiptCheckbox").check();

      // Uncheck #readReceiptCheckbox
      page.frame("userHtmlFrame").locator("#readReceiptCheckbox").uncheck();

      // Click text=Send 3 emails
      page.frame("userHtmlFrame").locator("text=Send 3 emails").click();

      // Click text=All emails have been sent!
      page.frame("userHtmlFrame").locator("text=All emails have been sent!").click();

      // Click text=Close
      page.frame("userHtmlFrame").locator("text=Close").click();
    }
  }
}