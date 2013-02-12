package com.iso.claimsearch.test.common.pageobjects;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;


public class CSBottomPane extends LoadableComponent<CSBottomPane>
{
	@FindBy(how=How.LINK_TEXT, using="About ISO ClaimSearch")
	private WebElement aboutIsoClaimsearch;
	
	@FindBy(how=How.LINK_TEXT, using="User Manuals and Guides")
	private WebElement userManualsAndGuides;
	
	@FindBy(how=How.LINK_TEXT, using="User Profile")
	private WebElement userProfile;
	
	

	private WebDriver driver;
	
	public CSBottomPane()
	{
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error 
	{
		assertTrue(aboutIsoClaimsearch != null);
		assertTrue(userManualsAndGuides != null);
		assertTrue(userProfile != null);
		
	}

	@Override
	protected void load() 
	{
		
	}
	
	
}