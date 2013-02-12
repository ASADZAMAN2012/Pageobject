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

public class ClaimSubmissionBasicInfoPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(ClaimSubmissionBasicInfoPage.class);
	private final static String pageUrl = "/UF/basic.asp";
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;	
	
	@FindBy(how=How.CLASS_NAME, using="CRHeader")
	private WebElement CRHeader;	
	
	@FindBy(how=How.NAME, using="cboPolType")
	private WebElement cboPolType;
	
	@FindBy(how=How.NAME, using="txtLOLAdd")
   	private WebElement txtLOLAdd;
       
    @FindBy(how=How.NAME, using="txtLOLCity")
   	private WebElement txtLOLCity;
       
    @FindBy(how=How.NAME, using="txtLOLState")
   	private WebElement txtLOLState;
       
    @FindBy(how=How.NAME, using="txtLossDesc")
   	private WebElement txtLossDesc;	
	
	@FindBy(how=How.NAME, using="txtPRIAdd")
	private WebElement txtPRIAdd;	
	 
	@FindBy(how=How.NAME, using="txtPRICity")
	private WebElement txtPRICity;	
	 
	@FindBy(how=How.NAME, using="txtPRIState")
	private WebElement txtPRIState;	
	 
	@FindBy(how=How.NAME, using="selRRECode")
	private WebElement selRRECode;
	
	@FindBy(how=How.NAME, using="chkSIIndicatorYes")
	private WebElement chkSIIndicatorYes;
    
	@FindBy(how=How.NAME, using="btnNext")
	private WebElement btnNext;
	
	private WebDriver driver;
	
	public ClaimSubmissionBasicInfoPage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	public ClaimSubmissionBasicInfoPage(String domain)
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{		
		LOG.info(CRHeader.getText());		
		assertEquals("Basic Information",CRHeader.getText());
        LOG.info("Verification: 'Basic Information' text should be displayed : PASSED");
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public ClaimSubmissionBasicInfoPage verifyClaimAndPolicyNumber(String policyNum, String calimNum){
		assertTrue(BrowserDriver.verifyTextPresent(calimNum));
        LOG.info("Verification: Claim Number - '" + calimNum + "' should be displayed : PASSED");
				
		assertTrue(BrowserDriver.verifyTextPresent(policyNum));
        LOG.info("Verification: Policy Number - '" + policyNum + "' should be displayed : PASSED");       
		
		return new ClaimSubmissionBasicInfoPage();
	}
	
	public ClaimSubmissionBasicInfoPage enterLocationOfLoss(String policyType,String address, String city,String state, String desc){		
		new Select(cboPolType).selectByVisibleText(policyType);
		LOG.info("Select '" + policyType + "' from Policy Type dropdown: PASSED");
		
		LOG.info("****** ENTER LOCATION OF LOSS SECTION *********");
		clearAndType(txtLOLAdd,address,"Address");
		clearAndType(txtLOLCity,city,"City");
		clearAndType(txtLOLState,state,"State");
		clearAndType(txtLossDesc,desc,"Loss Description");
		
		return new ClaimSubmissionBasicInfoPage();
	}

	public ClaimSubmissionBasicInfoPage enterPhysicalRiskInformation(String address, String city,String state){
		LOG.info("****** ENTER PHYSICAL RISK INFORMATION SECTION *********");
		
		clearAndType(txtPRIAdd,address,"Address");
		clearAndType(txtPRICity,city,"City");
		clearAndType(txtPRIState,state,"State");           
		
		return new ClaimSubmissionBasicInfoPage();
	}

	public ClaimSubmissionBasicInfoPage enterMedicareReporting(String rreCode) throws Exception{
		LOG.info("****** ENTER FOR MEDICARE REPORTING SECTION *********");
		
		//Select [[RRECode]] from drop down
        new Select(selRRECode).selectByValue(rreCode);
        LOG.info("Select '" + rreCode + "' from 'RRE Code' drop down : Passed ");
        
        Thread.sleep(3000);
        
        //Click yes to self insured idicator
        chkSIIndicatorYes.sendKeys(Keys.SPACE);
        LOG.info("Click 'YES' for Self Insured Indicator : Passed");		
		
		return new ClaimSubmissionBasicInfoPage();		
	}
	
	public InvolvedPartyInformationPage clickNext(){
		btnNext.click();
		LOG.info("Click 'Next' Button");
	
		return new InvolvedPartyInformationPage();	
	}
}