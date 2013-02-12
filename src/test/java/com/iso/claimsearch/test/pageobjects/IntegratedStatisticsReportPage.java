package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

public class IntegratedStatisticsReportPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(IntegratedStatisticsReportPage.class);
	private final static String pageUrl = "/AcctMgmt/reports/integratedStatisticsReport.action";
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;	
	
	@FindBy(how=How.CLASS_NAME, using="OnlineReportHeader")
	private WebElement OnlineReportHeader;
	
	@FindBy(how=How.LINK_TEXT, using="Print")
	private WebElement Print;
	
	@FindBy(how=How.LINK_TEXT, using="Download to Excel")
	private WebElement DownloadtoExcel;
	
	@FindBy(how=How.ID, using="criteriaLevel")
	private WebElement criteriaLevel;
	
	@FindBy(how=How.ID, using="integratedStatisticsReport_criteria_duration")
	private WebElement Totals;	
	
	private WebDriver driver;
	
	public IntegratedStatisticsReportPage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	public IntegratedStatisticsReportPage(String domain)
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error 
	{		
		BrowserDriver.waitForElement(Print,10);
		assertTrue(OnlineReportHeader.getText().contains("ISO ClaimSearch Integrated Statistics Report"));		
		LOG.info("Verification: 'ISO ClaimSearch Integrated Statistics Report' Page has been verified: PASSED");
	}

	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public IntegratedStatisticsReportPage verifyIntegratedStatisticsReport(String companyName,String statsFor,String totals) throws Exception
	{			
		//Select [[StatsFor]] from the drop down list
        new Select(criteriaLevel).selectByVisibleText(statsFor);
        LOG.info("Select '" + statsFor + "' from Statistics for drop down : PASSED ");
        
        Thread.sleep(3000);
        
        //Select [[Totals]] from the drop down list
        new Select(Totals).selectByVisibleText(totals);
        LOG.info("Select '" + totals + "' from Totals drop down : PASSED ");
		  
        Thread.sleep(3000);
        
        //Click the download to excel link
        BrowserDriver.waitForElement(DownloadtoExcel,5);// Wait for  "Online Utilization Report" link until it is visible
        DownloadtoExcel.click();
        LOG.info("Click the Download to Excel link : Passed ");
        
        Thread.sleep(2000);
        
        //Verify the open or save dialog message
        if(BrowserDriver.getAlert().contains("Do you want to open or save this file?"))
        LOG.info("Verification - The open or save dialog message should be displayed: PASSED ");
        BrowserDriver.clickCancelOnAlert();
        LOG.info("Click the cancel dialog button : PASSED");
        
        return new IntegratedStatisticsReportPage();

	}
	
	
	
	
	
}