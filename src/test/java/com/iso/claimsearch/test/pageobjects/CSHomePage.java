package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.iso.claimsearch.test.common.pageobjects.CSBottomPane;
import com.iso.claimsearch.test.common.pageobjects.CSHeader;
import com.iso.claimsearch.test.common.pageobjects.Header;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;

public class CSHomePage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(CSHomePage.class);
	private final static String pageUrl = "/home/home.asp";
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;	
	
	@FindBy(how=How.LINK_TEXT, using="Account Management")
	private WebElement AccountManagement;
	
	@FindBy(how=How.LINK_TEXT, using="Claims Reporting")
	private WebElement ClaimsReporting;
	
	private WebDriver driver;
	
	public CSHomePage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	public CSHomePage(String domain)
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{		
		assertFalse(AccountManagement.equals(Exception.class));
		assertFalse(ClaimsReporting.equals(Exception.class));	
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public AccountManagementPage clickAccountManagement()
	{			
		AccountManagement.click();
		LOG.info("Click 'Account Management' Link: PASSED");
		return new AccountManagementPage();		
	}
	
	public ClaimsReportingPage clickClaimsReporting()
	{			
		ClaimsReporting.click();
		LOG.info("Click 'Claims Reporting' Link: PASSED");
		return new ClaimsReportingPage();		
	}
}