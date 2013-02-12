package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;

import com.iso.claimsearch.test.common.pageobjects.CSBottomPane;
import com.iso.claimsearch.test.common.pageobjects.CSHeader;
import com.iso.claimsearch.test.ui.common.core.PageBase;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;

public class ClaimSubmissionPage extends ApplicationPageBase 
{
	private final static String pageUrl = "/ClaimsReporting/dcpc.asp";
	private static final Logger LOG = Logger.getLogger(ClaimSubmissionPage.class);
	
	@FindBy(how=How.CLASS_NAME, using="CRHeader")
	private WebElement CRHeader;	
	
	@FindBy(how=How.LINK_TEXT, using="Claim Submission")
	private WebElement ClaimSubmission;
	
	@FindBy(how=How.NAME, using="btnNext")
	private WebElement btnNext;
	
	@FindBy(how=How.NAME, using="txtPolicyNo")
	private WebElement txtPolicyNo;
	
	@FindBy(how=How.NAME, using="txtDOL")
	private WebElement txtDOL;
	
	@FindBy(how=How.NAME, using="txtClaimNo")
	private WebElement txtClaimNo;
	
	@FindBy(how=How.NAME, using="cboInsCode")
	private WebElement cboInsCode;
	
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;
	
	private WebDriver driver;
	
	public ClaimSubmissionPage()
	{	
		super(BrowserDriver.getUrl());
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public ClaimSubmissionPage(String domain)
	{
		super(pageUrl, domain);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{
		LOG.info(CRHeader.getText());		
		assertEquals("Loss & Policy Information",CRHeader.getText());
        LOG.info("Verification: 'Loss & Policy Information' text should be displayed on Claimsearch Reports page : PASSED");
		
		assertFalse(txtPolicyNo.equals(Exception.class));				
		assertFalse(cboInsCode.equals(Exception.class));
		assertFalse(txtClaimNo.equals(Exception.class));
		assertFalse(txtDOL.equals(Exception.class));
		LOG.info("Verification: Claims Reporting Verification PASSED");
	}

	@Override
	protected void load() 
	{
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public ClaimSubmissionPage clickClaimSubmission(){
		ClaimSubmission.click();
		LOG.info("Click 'Claim Submission' Link");	
		return new ClaimSubmissionPage();
	}
	
	public ClaimsReportingPage verifyExistingClaims(String officeCode, String claimNumber, String dateOfLoss, String policyNumber){
		new Select(cboInsCode).selectByValue(officeCode);
		LOG.info("Select OfficeCode -'" + officeCode + "' From drop down: PASSED");
		
		clearAndType(txtClaimNo,claimNumber,"Claim Number");	
		clearAndType(txtDOL,dateOfLoss,"Date Of Loss(MMDDYYYY)");
		clearAndType(txtPolicyNo,policyNumber,"Policy Number");
		btnNext.click();
		LOG.info("Click 'Next' Button");
		
		BrowserDriver.clickOkOnAlert();
		
		BrowserDriver.waitForElement(driver.findElement(By.name("btnSubmit")), 10);
		
		 //Verify expected text - 'Claim Summary' found on Claim Summary page
		assertTrue(BrowserDriver.verifyTextPresent("Claim Summary"));
		LOG.info("Verification - Verify expected text - 'Claim Summary' found on Claim Summary page : PASSED");

        //Click 'Recall Search' checkbox
        driver.findElement(By.name("chkRecall")).click();
        LOG.info("Click 'Recall Search' checkbox : Passed ");
        
        //Click 'Submit' button
        driver.findElement(By.name("btnSubmit")).click();
        LOG.info("Click 'Submit' button : Passed ");
        
        //Verify pop-up message 'You are about to update this record to the database. Continue?' shown and return to page when click ok
        BrowserDriver.clickOkOnAlert();
        
        BrowserDriver.waitForElement(driver.findElement(By.name("btnView")), 5);
        
        //Verify expected text - 'Thank you for your submission. It has been received successfully' found
        assertTrue(BrowserDriver.verifyTextPresent("Thank you for your submission. It has been received successfully"));
        LOG.info("Verification:'Thank you for your submission. It has been received successfully' text should be displayed: PASSED");
        
        //Click the view reports button
        driver.findElement(By.name("btnView")).click();
        LOG.info("Click the view reports button : PASSED");       
		
		return new ClaimsReportingPage();
	}
	
	public String enterClaimDetails(String officeCode, String claimNumber, String dateOfLoss, String policyNumber){
		new Select(cboInsCode).selectByValue(officeCode);
		LOG.info("Select OfficeCode -'" + officeCode + "' From drop down: PASSED");
		
		clearAndType(txtClaimNo,claimNumber,"Claim Number");	
		clearAndType(txtDOL,dateOfLoss,"Date Of Loss(MMDDYYYY)");
		
		Format formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		String date = formatter.format(new Date());
		
		policyNumber = policyNumber + date;
		
		clearAndType(txtPolicyNo,policyNumber,"Policy Number");
		btnNext.click();
		LOG.info("Click 'Next' Button");
		
		return policyNumber;				
	}
		
	public ClaimSubmissionBasicInfoPage navigateToBasicInformationPage(){		
		return new ClaimSubmissionBasicInfoPage();
	}
	
	
}