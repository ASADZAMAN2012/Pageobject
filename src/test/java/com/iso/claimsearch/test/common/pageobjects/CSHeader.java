package com.iso.claimsearch.test.common.pageobjects;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;


public class CSHeader extends LoadableComponent<CSHeader> 
{
	@FindBy(how=How.LINK_TEXT, using="Home")
	private WebElement home;
	
	@FindBy(how=How.LINK_TEXT, using="Claims Reporting")
	private WebElement claimsReporting;
	
	@FindBy(how=How.LINK_TEXT, using="Claims Inquiry")
	private WebElement claimsInquiry;
	
	@FindBy(how=How.LINK_TEXT, using="VIN Decoding")
	private WebElement vinDecoding;
	
	@FindBy(how=How.LINK_TEXT, using="NICB Submission")
	private WebElement nicbSub;
	
	@FindBy(how=How.LINK_TEXT, using="SIU Case Manager")
	private WebElement siuCaseManager;
	
	@FindBy(how=How.LINK_TEXT, using="Decision Net")
	private WebElement decisionNet;
	
	@FindBy(how=How.LINK_TEXT, using="OFAC")
	private WebElement ofac;
	
	@FindBy(how=How.LINK_TEXT, using="Account Management")
	private WebElement accMgt;
	
	@FindBy(how=How.LINK_TEXT, using="Log Out")
	private WebElement logout;
	
	
	private WebDriver driver;
	
	private static final Logger LOG = Logger.getLogger(CSHeader.class);
	
	public CSHeader()
	{
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error 
	{
		assertTrue(home != null);
		assertTrue(claimsReporting != null);
		assertTrue(claimsInquiry!=null);
		assertTrue(vinDecoding!=null);
		assertTrue(nicbSub!=null);
		assertTrue(siuCaseManager!=null);
		assertTrue(decisionNet!=null);
		assertTrue(ofac!=null);
		assertTrue(accMgt!=null);
		assertTrue(logout!=null);

	}

	@Override
	protected void load() 
	{
		
	}
	
	public void clickLogout()
	{
		logout.click();
		LOG.info("Logged out Successfully");
	}
	
	
	
}