package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;

import com.iso.claimsearch.test.common.pageobjects.CSBottomPane;
import com.iso.claimsearch.test.common.pageobjects.CSHeader;
import com.iso.claimsearch.test.ui.common.core.PageBase;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;

public class ClaimsReportingPage extends ApplicationPageBase 
{
	private final static String pageUrl = "/ClaimsReporting/returns_tbl.asp";
	private static final Logger LOG = Logger.getLogger(ClaimsReportingPage.class);
	
	@FindBy(how=How.LINK_TEXT, using="Claim Submission")
	private WebElement ClaimSubmission;
	
	@FindBy(how=How.NAME, using="btnPNR")
	private WebElement printNew;
	
	@FindBy(how=How.XPATH,using="(//form/table[4]/tbody/tr/td/table/tbody/tr)")
	private List<WebElement> reportList;
	
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;
	
	private WebDriver driver;
	
	public ClaimsReportingPage()
	{	
		super(BrowserDriver.getUrl());
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public ClaimsReportingPage(String domain)
	{
		super(pageUrl, domain);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{
		assertTrue(BrowserDriver.verifyTextPresent("'Submitted Report +' means that no matching claims were found"));
        LOG.info("Verification: 'Submitted Report +' means that no matching claims were found' text should be displayed on Claimsearch Reports page : PASSED");
		
		assertFalse(ClaimSubmission.equals(Exception.class));				
		LOG.info("Verification: Claims Reporting Verification PASSED");
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public ClaimSubmissionPage clickClaimSubmission(){
		ClaimSubmission.click();
		LOG.info("Click 'Claim Submission' Link");	
		return new ClaimSubmissionPage();
	}
	
	public ClaimsReportingPage verifyClaimSubmission(){
		BrowserDriver.waitForElement(printNew,5);
		
		int totalRecord = reportList.size();
        Assert.assertTrue("Verify the record exists in the table : Failed", totalRecord>3);
        LOG.info("Verification: Record should be available in the table : PASSED");
    
        return new ClaimsReportingPage();
	}
	
	
}