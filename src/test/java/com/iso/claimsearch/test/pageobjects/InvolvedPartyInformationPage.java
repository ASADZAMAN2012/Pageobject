package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
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

public class InvolvedPartyInformationPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(InvolvedPartyInformationPage.class);
	private final static String pageUrl = "/UF/party.asp";
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;	
	
	@FindBy(how=How.CLASS_NAME, using="CRHeader")
	private WebElement CRHeader;	
	
	@FindBy(how=How.NAME, using="optInsuredHasClaim")
	private WebElement optInsuredHasClaim;
	
	@FindBy(how=How.NAME, using="txtLastName")
	private WebElement txtLastName;
	
	@FindBy(how=How.NAME, using="txtFirstName")
	private WebElement txtFirstName;
	
	@FindBy(how=How.NAME, using="txtAddress")
	private WebElement txtAddress;
	
	@FindBy(how=How.NAME, using="txtCity")
	private WebElement txtCity;
	
	@FindBy(how=How.NAME, using="txtState")
	private WebElement txtState;
	
	@FindBy(how=How.NAME, using="optGender")
	private WebElement optGender;
	
	@FindBy(how=How.NAME, using="txtDOB")
	private WebElement txtDOB;
	
	@FindBy(how=How.NAME, using="txtSSN")
	private WebElement txtSSN;
	
	@FindBy(how=How.NAME, using="txtHomePhone")
	private WebElement txtHomePhone;
	
	@FindBy(how=How.NAME, using="txtOccupation")
	private WebElement txtOccupation;
	
	@FindBy(how=How.NAME, using="chkMedEligYes")
	private WebElement chkMedEligYes;
    
	@FindBy(how=How.NAME, using="btnNext")
	private WebElement btnNext;
	
	private WebDriver driver;
	
	public InvolvedPartyInformationPage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	public InvolvedPartyInformationPage(String domain)
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{				
		assertEquals("Involved Party Information",CRHeader.getText());
        LOG.info("Verification: 'Involved Party Information' text should be displayed : PASSED");
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public InvolvedPartyInformationPage verifyClaimAndPolicyNumber(String policyNum, String calimNum){
		assertTrue(BrowserDriver.verifyTextPresent(calimNum));
        LOG.info("Verification: Claim Number - '" + calimNum + "' should be displayed : PASSED");
				
		assertTrue(BrowserDriver.verifyTextPresent(policyNum));
        LOG.info("Verification: Policy Number - '" + policyNum + "' should be displayed : PASSED");       
		
		return new InvolvedPartyInformationPage();
	}
	
	public InvolvedPartyInformationPage enterPartyInfo1(String lastName,String firstName,String address, String city,String state){		
		optInsuredHasClaim.sendKeys(Keys.SPACE);
		LOG.info("Select 'YES' for 'Insured has a claim': PASSED");
		
		clearAndType(txtLastName,lastName,"Last Name");
		clearAndType(txtFirstName,firstName,"First Name");
		clearAndType(txtAddress,address,"Address");
		clearAndType(txtCity,city,"City");
		clearAndType(txtState,state,"State");
		
		return new InvolvedPartyInformationPage();
	}

	public InvolvedPartyInformationPage enterPartyInfo2(String dob,String ssn,String homePhone,String occupation){		
		optGender.sendKeys(Keys.SPACE);
		LOG.info("Select 'Male' for 'Gender': PASSED");
		
		clearAndType(txtDOB,dob,"DOB");
		clearAndType(txtSSN,ssn,"SSN");
		clearAndType(txtHomePhone,homePhone,"Home Phone");		
		clearAndType(txtOccupation,occupation,"Occupation");
		
		chkMedEligYes.sendKeys(Keys.SPACE);
		LOG.info("Select 'YES' for Medicare Eligible: PASSED");	    
		
		clickNext();  
		
		return new InvolvedPartyInformationPage();
	}	
	
	public InvolvedPartyInformationPage enterCoverageInformation(){
		assertEquals("Coverage Information",CRHeader.getText());
        LOG.info("Verification: 'Coverage Information' text should be displayed : PASSED");
		
        //Click medical payments
        driver.findElement(By.name("chkMPAY")).sendKeys(Keys.SPACE);
        LOG.info("Click Medical Payments : Passed");
        
        clickNext(); 
        
		return new InvolvedPartyInformationPage();
	}
	
	public void enterGeneralCasualtyInformation(String allegedDamaged){		   
        //Verify page General Casualty Information        
        assertEquals("General Casualty Information",CRHeader.getText());
        LOG.info("Test step - General Casualty Information : Passed");
        
        //Input [[AllegedDamage]] in text field
        clearAndType(driver.findElement(By.name("txtAllegInjuries")),allegedDamaged,"Alleged Injuries /Property Damage");
        
        clickNext();       
	}	
	
	public void clickNext(){
		btnNext.click();
		LOG.info("Click 'Next' Button");
	}	
	
	public GeneralCasualtyInformationAdditionalPage navigateToGeneralCasualtyInformationAddPage(){
		return new GeneralCasualtyInformationAdditionalPage();
	}
}