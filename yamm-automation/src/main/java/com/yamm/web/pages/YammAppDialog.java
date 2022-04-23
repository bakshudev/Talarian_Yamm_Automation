package com.yamm.web.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class YammAppDialog {
	
	 // Hide all web elements and declare them as private final
	  private final FrameLocator yammFrame;
	  
	  private final Locator sentCountMessage;
	  private final Locator closeButton;
	  private final Locator alertMessage;
	  private final Locator senderName;
	  private final Locator emailDraftList;
	  private final Locator trackCheckbox;
	  private final Locator sendButton;

	  public YammAppDialog(Page page) {
	    yammFrame = page.frameLocator("[src*=\"macros\"]").frameLocator("#sandboxFrame").frameLocator("#userHtmlFrame");
	    sentCountMessage = yammFrame.locator("#mainPanel div:nth-child(1)");
	    closeButton = yammFrame.locator("#mainPanel button:has-text('Close')");
	    alertMessage = yammFrame.locator("div.yamm__alert_message_container");
	    
	    senderName = yammFrame.locator("#senderName_input");
	    emailDraftList = yammFrame.locator("#drafts_list");
	    trackCheckbox = yammFrame.locator("#readReceiptCheckbox");
	    sendButton = yammFrame.locator("#sendButton");
	  }
	  
	  	// Expose only user services that can be done on the Yamm App Dialog
	  	// We need to parameterize this method with different send actions
		public void performSendActions(String sender, String emailDraft)
		{
			senderName.fill(sender);
			emailDraftList.selectOption(emailDraft);
			trackCheckbox.uncheck();
			sendButton.click();
			closeButton.focus(); // Wait for the close button to appear	
		}
		
		public String getSentCountMessage()
		{
			return sentCountMessage.innerText();
		}
		
		public void close()
		{
			closeButton.click();
		}
		
		public String getAlertMessage()
		{
			return alertMessage.innerText();
		}

	}