package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.iso.claimsearch.test.common.pageobjects.CSBottomPane;
import com.iso.claimsearch.test.common.pageobjects.CSHeader;
import com.iso.claimsearch.test.common.pageobjects.Header;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;

public class OnlineUtilizationReportPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(OnlineUtilizationReportPage.class);
	private final static String pageUrl = "/AcctMgmt/reports/onlineUtilReport";
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;	
	
	@FindBy(how=How.CLASS_NAME, using="OnlineReportHeader")
	private WebElement OnlineReportHeader;
	
	@FindBy(how=How.LINK_TEXT, using="Print")
	private WebElement Print;
	
	private WebDriver driver;
	
	public OnlineUtilizationReportPage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	public OnlineUtilizationReportPage(String domain)
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{		
		BrowserDriver.waitForElement(Print,10);
		assertTrue(OnlineReportHeader.getText().contains("ISO ClaimSearch Online Utilization Report"));		
		LOG.info("Verification: 'ISO ClaimSearch Online Utilization Report' Page has been verified: PASSED");
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public OnlineUtilizationReportPage verifyOnlineUtilizationReport(String companyName,String cid)
	{			
		driver.findElement(By.linkText(cid)).click();
		LOG.info("Click Company - '" + cid + "' on 'ISO ClaimSearch Online Utilization Report' Page");
		
		//Verify Company text found on Utilization Report page			
		assertTrue(BrowserDriver.verifyTextPresent("Company: " + cid));
        LOG.info("Verification: 'Company: " + cid + "' should be displayed Online Utilization Report page : PASSED");
		
		return new OnlineUtilizationReportPage();
	}
	
	
	
	
	
}