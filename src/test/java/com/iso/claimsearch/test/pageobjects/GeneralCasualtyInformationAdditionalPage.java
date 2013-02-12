package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.iso.claimsearch.test.common.pageobjects.CSBottomPane;
import com.iso.claimsearch.test.common.pageobjects.CSHeader;
import com.iso.claimsearch.test.common.pageobjects.Header;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;

public class GeneralCasualtyInformationAdditionalPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(GeneralCasualtyInformationAdditionalPage.class);
	private final static String pageUrl = "/UF/cas_add_Info.asp";
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;	
	
	@FindBy(how=How.CLASS_NAME, using="CRHeader")
	private WebElement CRHeader;	
	
	@FindBy(how=How.NAME, using="txtICD9_1")
	private WebElement txtICD9_1;
	
	@FindBy(how=How.NAME, using="chkPLiabForCMSNoMassTort")
	private WebElement chkPLiabForCMSNoMassTort;
	
	@FindBy(how=How.NAME, using="txtGenericName")
	private WebElement txtGenericName;
	
	@FindBy(how=How.NAME, using="txtBrandName")
	private WebElement txtBrandName;
	
	@FindBy(how=How.NAME, using="txtManufacturer")
	private WebElement txtManufacturer;
	
	@FindBy(how=How.NAME, using="txtAllegHarm")
	private WebElement txtAllegHarm;
	
	@FindBy(how=How.NAME, using="txtCMSDOI")
	private WebElement txtCMSDOI;
	
	@FindBy(how=How.NAME, using="txtStateVenue")
	private WebElement txtStateVenue;
	
	@FindBy(how=How.NAME, using="txtFaultInsLimitDlr")
	private WebElement txtFaultInsLimitDlr;
	
	@FindBy(how=How.NAME, using="txtFaultInsLimitCent")
	private WebElement txtFaultInsLimitCent;
	
	@FindBy(how=How.NAME, using="chkORMYes")
	private WebElement chkORMYes;
	
	@FindBy(how=How.NAME, using="chkMedEligYes")
	private WebElement chkMedEligYes;
    
	@FindBy(how=How.NAME, using="btnNext")
	private WebElement btnNext;
	
	private WebDriver driver;
	
	public GeneralCasualtyInformationAdditionalPage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	public GeneralCasualtyInformationAdditionalPage(String domain)
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{				
		assertEquals("General Casualty Information (Additional)",CRHeader.getText());
        LOG.info("Verification: 'General Casualty Information (Additional)' text should be displayed : PASSED");
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public GeneralCasualtyInformationAdditionalPage verifyClaimAndPolicyNumber(String policyNum, String calimNum){
		assertTrue(BrowserDriver.verifyTextPresent(calimNum));
        LOG.info("Verification: Claim Number - '" + calimNum + "' should be displayed : PASSED");
				
		assertTrue(BrowserDriver.verifyTextPresent(policyNum));
        LOG.info("Verification: Policy Number - '" + policyNum + "' should be displayed : PASSED");       
		
		return new GeneralCasualtyInformationAdditionalPage();
	}
	
	public GeneralCasualtyInformationAdditionalPage enterMedicareReportingInfo1(String icd,String generic,String brand, String manufacturer,String allegedHarm){	
		clearAndType(txtICD9_1,icd,"ICD9 Codes");
		chkPLiabForCMSNoMassTort.sendKeys(Keys.SPACE);
		LOG.info("Select 'Yes (and is mass tort)' for 'Product Liability': PASSED");
		
		clearAndType(txtGenericName,generic,"Product Generic Name");
		clearAndType(txtBrandName,brand,"Product Brand Name");
		clearAndType(txtManufacturer,manufacturer,"Product Manufacturer");
		clearAndType(txtAllegHarm,allegedHarm,"Product Alleged Harm");
		
		return new GeneralCasualtyInformationAdditionalPage();
	}

	public GeneralCasualtyInformationAdditionalPage enterMedicareReportingInfo2(String cmsdoi,String stateVenue,String dlr, String cent){	
		clearAndType(txtCMSDOI,cmsdoi,"CMS Date Of Incident");		
		clearAndType(txtStateVenue,stateVenue,"State Of Venue");		
		clearAndType(txtFaultInsLimitDlr,dlr,"No Fault Insurance Limit(NFIL)");		
		clearAndType(txtFaultInsLimitCent,cent,"No Fault Insurance Limit(NFIL)");
		
		chkORMYes.sendKeys(Keys.SPACE);
		LOG.info("Select 'Yes' for 'ORM': PASSED");
		
		clickNext();
		
		return new GeneralCasualtyInformationAdditionalPage();
	}
	
	public GeneralCasualtyInformationAdditionalPage enterCasualtyCoverageInformation(String lossType,String claimStatus)	
		{ 
			//Verify page Casualy Coverage Information		
			assertEquals("Casualty Coverage Information",CRHeader.getText());
	        LOG.info("Verification: 'Casualty Coverage Information' text should be displayed : PASSED");
            
            
            //Select [[LossType]] from drop down
            new Select(driver.findElement(By.name("cboLossType"))).selectByVisibleText(lossType);
            LOG.info("Select '" + lossType + "' from 'Loss Type:' drop down : Passed ");
            
            //Select [[ClaimStatus]] from drop down
            new Select(driver.findElement(By.name("cboClaimStatus"))).selectByVisibleText(claimStatus);
            LOG.info("Test step - Select '" + claimStatus + "' from drop down : Passed ");
            
            //Click 'Next' button
            clickNext();
			
			return new GeneralCasualtyInformationAdditionalPage();
		}
		
	public void submitClaim() throws Exception{
		 //Verify page Claim Summary
		assertEquals("Claim Summary",CRHeader.getText());        
		LOG.info("Verification: 'Claim Summary' text should be displayed : PASSED");
        
        //Click the submit button
        driver.findElement(By.name("btnSubmit")).click();
        LOG.info("Click the Submit button : Passed ");        
        
        LOG.info("Test step - Verify add to database message : " + BrowserDriver.getAlert());        
        BrowserDriver.clickOkOnAlert();
        
        Thread.sleep(3000);
        
        //Verify page Claim Summary
        assertTrue(BrowserDriver.verifyTextPresent("Thank you for your submission"));
        LOG.info("Verification:'Thank you for your submission' text should be displayed : PASSED");		
	}
	
	public void clickNext(){
		btnNext.click();
		LOG.info("Click 'Next' Button");
	}	
}