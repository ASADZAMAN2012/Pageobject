package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.hsqldb.lib.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.util.StringUtils;
import com.iso.claimsearch.test.common.pageobjects.CSBottomPane;
import com.iso.claimsearch.test.common.pageobjects.CSHeader;
import com.iso.claimsearch.test.common.pageobjects.Header;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;

public class ClaimDirectorCustomizerPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(ClaimDirectorCustomizerPage.class);
	private final static String pageUrl = "AcctMgmt/claimdircustomizer/advisoryListView.action";
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;	
	
	@FindBy(how=How.LINK_TEXT, using="Advisory List")
	private WebElement AdvisoryList;
	
	@FindBy(how=How.LINK_TEXT, using="Rules")
	private WebElement Rules;
	
	@FindBy(how=How.LINK_TEXT, using="Notification")
	private WebElement Notification;

	@FindBy(how=How.LINK_TEXT, using="Frequencies")
	private WebElement Frequencies;
	
	@FindBy(how=How.LINK_TEXT, using="General")
	private WebElement General;
	
	@FindBy(how=How.LINK_TEXT, using="Change History")
	private WebElement ChangeHistory;
	
	@FindBy(how=How.CLASS_NAME, using="OnlineReportHeader")
	private WebElement OnlineReportHeader;
	
	
	private WebDriver driver;
	
	public ClaimDirectorCustomizerPage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	public ClaimDirectorCustomizerPage(String domain)
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{		
		assertEquals("ClaimDirector Customizer",OnlineReportHeader.getText());		
		LOG.info("Verification: 'ClaimDirector Customizer' Page has been verified: PASSED");
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public ClaimDirectorCustomizerPage verifyClaimDirectorCustomizer(String companyName)
	{			
		assertTrue(BrowserDriver.verifyTextPresent(companyName));
		LOG.info("Verification: '" + companyName + "' Company should be displayed: PASSED");
		
		assertTrue(AdvisoryList.isDisplayed());
		LOG.info("Verification: 'Advisory List' Link should be displayed: PASSED");
		
		Rules.click();
		LOG.info("Click on 'Rules' Link");
		
		Notification.click();
		LOG.info("Click on 'Notification' Link");
		
		Frequencies.click();
		LOG.info("Click on 'Frequencies' Link");
		
		General.click();
		LOG.info("Click on 'General' Link");		

		ChangeHistory.click();
		LOG.info("Click on 'Change History' Link");

		
		return new ClaimDirectorCustomizerPage();
	}
	
	
	public ClaimDirectorCustomizerPage addAdvisory(String companyName,String category, String business)
	{	
		AdvisoryList.click();
		LOG.info("Click on 'Advisory List' Link");
		
		assertTrue(BrowserDriver.verifyTextPresent(companyName));
		LOG.info("Verification: '" + companyName + "' Company should be displayed: PASSED");
		
		driver.findElement(By.name("cmdAdd")).click();
		LOG.info("Click on 'Add' Button");
				
		new Select(driver.findElement(By.id("criteria"))).selectByVisibleText(category);
		LOG.info("Select Category - '" + category + "' from the dropdown list");
				
		driver.findElement(By.id("txtBusName")).sendKeys(business);
		LOG.info("Enter "+business+" in the business field");
		
		LOG.info("Get Current System date");
		Format formatter = new SimpleDateFormat("M/d/yyyy");
		String date = formatter.format(new Date());
		
		driver.findElement(By.name("cmdSub")).click();
		LOG.info("Click on Ok button");
		
		BrowserDriver.waitForElement(OnlineReportHeader, 10);
		
		assertTrue(BrowserDriver.verifyTextPresent(date));
		LOG.info("Verification: Date - '" + date + "' should appear on page: PASSED");
		
		assertTrue(BrowserDriver.verifyTextPresent(business));
		LOG.info("Verification: Business - '" + business + "' should appear on page: PASSED");
				
		LOG.info("Click on Notification Link");
		driver.findElement(By.linkText("Notification")).click();
		
		return new ClaimDirectorCustomizerPage();
	}
	
	
	
	
	
}