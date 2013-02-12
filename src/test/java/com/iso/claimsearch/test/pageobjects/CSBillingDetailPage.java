package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.iso.claimsearch.test.common.pageobjects.CSBottomPane;
import com.iso.claimsearch.test.common.pageobjects.CSHeader;
import com.iso.claimsearch.test.common.pageobjects.Header;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;

public class CSBillingDetailPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(CSBillingDetailPage.class);
	private final static String pageUrl = "/AcctMgmt/invoices/index.action";
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;	
	
	@FindBy(how=How.NAME, using="criteria.code")
	private WebElement indexCriteriaCode;
	
	@FindBy(how=How.XPATH, using="(//input[@value='List Invoices'])")
	private WebElement listInvoices;
	
	private WebDriver driver;
	
	public CSBillingDetailPage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	public CSBillingDetailPage(String domain)
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{		
		assertTrue(BrowserDriver.getPageSource().contains("ISO ClaimSearch Billing Detail"));
		assertFalse(indexCriteriaCode.equals(Exception.class));
		assertFalse(listInvoices.equals(Exception.class));	
		LOG.info("Verification: 'ISO ClaimSearch Billing Detail' Page has been verified: PASSED");
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public CSBillingDetailPage verifyBillingDetails(String customerNumber, String invoiceNumber)
	{			
		clearAndType(indexCriteriaCode,customerNumber, "Customer Number");
		listInvoices.click();
		LOG.info("Click 'List Invoices' Button");	
		
		assertTrue(BrowserDriver.verifyTextPresent(invoiceNumber));
		LOG.info("Verification: '" + invoiceNumber + "' Invoice Number should be displayed: PASSED");
		
		driver.findElement(By.linkText(invoiceNumber)).click();
		LOG.info("Click Invoice Number - '" + invoiceNumber + "'");
		
		assertTrue(BrowserDriver.verifyTextPresent(invoiceNumber));
		LOG.info("Verification: '" + invoiceNumber + "' Invoice Number should be displayed: PASSED");
			
		return new CSBillingDetailPage();
	}
	
	
	
	
	
}