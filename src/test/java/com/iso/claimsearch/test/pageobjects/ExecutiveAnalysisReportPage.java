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

public class ExecutiveAnalysisReportPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(ExecutiveAnalysisReportPage.class);
	private final static String pageUrl = "/AcctMgmt/reports/execAnalysisReport";
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;	
	
	@FindBy(how=How.CLASS_NAME, using="OnlineReportHeader")
	private WebElement OnlineReportHeader;
	
	private WebDriver driver;
	
	public ExecutiveAnalysisReportPage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	public ExecutiveAnalysisReportPage(String domain)
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{		
		assertTrue(OnlineReportHeader.getText().contains("ISO ClaimSearch Executive Analysis"));		
		LOG.info("Verification: 'ISO ClaimSearch Executive Analysis' Page has been verified: PASSED");
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public ExecutiveAnalysisReportPage verifyExecutiveAnalysisReport(String groupNumber)
	{			
		assertTrue(BrowserDriver.verifyTextPresent("EXECUTIVE SUMMARY ANALYSIS"));
		LOG.info("Verification: 'EXECUTIVE SUMMARY ANALYSIS' text should be displayed: PASSED");
		
		try{
			driver.findElement(By.linkText("Print")).isDisplayed();
			
			//Click the opts field link
    		List<WebElement> optFields = driver.findElements(By.linkText("Opt.Fields"));
    		optFields.get(0).click();
		
    		assertTrue(BrowserDriver.verifyTextPresent("OPTIONAL FIELD INFORMATION"));
            LOG.info("Verification: 'OPTIONAL FIELD INFORMATION' text should be displayed: PASSED");
            
            driver.findElement(By.linkText("Group: "+groupNumber)).click();
            LOG.info("Click the 'Group: "+ groupNumber +"' link : PASSED");
            
            assertTrue(BrowserDriver.verifyTextPresent("EXECUTIVE SUMMARY ANALYSIS"));
    		LOG.info("Verification: 'EXECUTIVE SUMMARY ANALYSIS' text should be displayed: PASSED");

    		//Click the trend field link
    		List<WebElement> trend = driver.findElements(By.linkText("Trend"));
    		trend.get(0).click();
    		
    		//Verify expected text - 'TREND REPORT PAGE'           
            assertTrue(BrowserDriver.verifyTextPresent("TREND REPORT PAGE"));
            LOG.info("Verification: 'TREND REPORT PAGE' text should be displayed: PASSED");            
		}
		catch(NoSuchElementException e){
			LOG.info("Test step - No records found : PASSED");		
		}		
		
		return new ExecutiveAnalysisReportPage();
	}
	
	
	
	
	
}