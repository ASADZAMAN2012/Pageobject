package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.iso.claimsearch.test.common.pageobjects.CSBottomPane;
import com.iso.claimsearch.test.common.pageobjects.CSHeader;
import com.iso.claimsearch.test.common.pageobjects.Footer;
import com.iso.claimsearch.test.common.pageobjects.Header;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;

public class AccountManagementPage extends ApplicationPageBase{
	private WebDriver driver;
	private static final String pageUrl = "/AcctMgmt/index.action";
	
	private static final Logger LOG = Logger.getLogger(AccountManagementPage.class);
	private Header header;	
	private Footer footer;	
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;
	
	
	@FindBy(how=How.LINK_TEXT, using="Decision Net Account Management")
	private WebElement DecisionNetAccountManagement;
	
	@FindBy(how=How.LINK_TEXT, using="ClaimDirector Account Management")
	private WebElement ClaimDirectorAccountManagement;

	@FindBy(how=How.LINK_TEXT, using="Online Utilization Report")
	private WebElement OnlineUtilizationReport;
	
	@FindBy(how=How.LINK_TEXT, using="Integrated Statistics Report")
	private WebElement IntegratedStatisticsReport;
	
	@FindBy(how=How.LINK_TEXT, using="ISO ClaimSearch Billing Detail")
	private WebElement ISOClaimSearchBillingDetail;
	
	@FindBy(how=How.LINK_TEXT, using="ISO ClaimSearch Management Reports")
	private WebElement ISOClaimSearchManagementReports;
	
	@FindBy(how=How.LINK_TEXT, using="Executive Analysis Report")
	private WebElement ExecutiveAnalysisReport;	
	
	public AccountManagementPage(){		
		super(BrowserDriver.getUrl());
		this.driver=BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}	
	
	public AccountManagementPage(String domain) {
		super(pageUrl, domain);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
		
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(BrowserDriver.getPageSource().contains("About Account Management"));
		assertFalse(DecisionNetAccountManagement.equals(Exception.class));
		assertFalse(ClaimDirectorAccountManagement.equals(Exception.class));
		assertFalse(OnlineUtilizationReport.equals(Exception.class));
		assertFalse(ISOClaimSearchManagementReports.equals(Exception.class));
		assertFalse(ISOClaimSearchBillingDetail.equals(Exception.class));
		assertFalse(IntegratedStatisticsReport.equals(Exception.class));
		assertFalse(ExecutiveAnalysisReport.equals(Exception.class));
		LOG.info("Verification: Account Management Page Verification PASSED");
	}


	@Override
	protected void load() 
	{
		super.load();
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	public CSBillingDetailPage navigateToISOClaimSearchBillingDetailPg(){
		ISOClaimSearchBillingDetail.click();
		LOG.info("Click 'ISO Claim Search Billing Detail' Link on Account Management Page");
		
		assertTrue(BrowserDriver.verifyTextPresent("This section provides claims and SIU Managers with access to the ISO ClaimSearch"));
		LOG.info("Verification: 'This section provides claims and SIU Managers with access to the ISO ClaimSearch' Message should be displayed: PASSED");
		
		ISOClaimSearchBillingDetail.click();
		LOG.info("Click 'ISO Claim Search Billing Detail' Link on 'ISO ClaimSearch Management Reports' Page");
		
		return new CSBillingDetailPage();
	}
	
	public DecisionNetBillingDetailPage navigateToDecisionNetBillingDetailPg(){		
		DecisionNetAccountManagement.click();
		LOG.info("Click 'Decision Net Account Management' Link on Account Management Page");
		
		assertTrue(BrowserDriver.verifyTextPresent("Decision Net Account Management"));
		LOG.info("Verification: 'Decision Net Account Management' Message should be displayed: PASSED");
		
		driver.findElement(By.linkText("Decision Net Billing Detail")).click();
		LOG.info("Click 'Decision Net Billing Detail' Link on 'Decision Net Account Management' Page");
		
		return new DecisionNetBillingDetailPage();		
	}

	
	public ExecutiveAnalysisReportPage navigateToExecutiveAnalysisReport(String companyName){		
		ExecutiveAnalysisReport.click();		
		LOG.info("Click 'Executive Analysis Report' Link on Account Management Page");
		
		assertTrue(BrowserDriver.verifyTextPresent("ISO ClaimSearch Management Reports"));
		LOG.info("Verification: 'ISO ClaimSearch Management Reports' Message should be displayed: PASSED");
		
		ExecutiveAnalysisReport.click();
		LOG.info("Click 'Executive Analysis Report' Link on 'ISO ClaimSearch Management Reports' Page");
		
		assertTrue(BrowserDriver.verifyTextPresent("Please choose the group/company you wish to view"));
		LOG.info("Verification: 'Please choose the group/company you wish to view' Message should be displayed: PASSED");

		new Select(driver.findElement(By.name("selGrp"))).selectByVisibleText(companyName);
		LOG.info("Verification: '" + companyName + "' Group/Company should selected: PASSED");
		
		driver.findElement(By.id("frmChooseCmp_0")).click();
		LOG.info("Click 'Next' Button");	
		
		return new ExecutiveAnalysisReportPage();
	}
	
	
	public ClaimDirectorCustomizerPage navigateToClaimDirectorPage(String companyName){		
		ClaimDirectorAccountManagement.click();		
		LOG.info("Click 'ClaimDirector Account Management' Link on Account Management Page");
		
		assertTrue(driver.findElement(By.linkText("ClaimDirector Customizer")).isDisplayed());
		assertTrue(BrowserDriver.verifyTextPresent("ClaimDirector Account Management"));
		LOG.info("Verification: 'ClaimDirector Account Management' Message should be displayed: PASSED");
		
		driver.findElement(By.linkText("ClaimDirector Customizer")).click();
		LOG.info("Click 'ClaimDirector Customizer' Link on 'ClaimDirector Account Management' Home Page");
		
		assertTrue(BrowserDriver.verifyTextPresent("Please choose the group/company you wish to view"));
		LOG.info("Verification: 'Please choose the group/company you wish to view' Message should be displayed: PASSED");

		new Select(driver.findElement(By.name("selGrp"))).selectByVisibleText(companyName);
		LOG.info("Verification: '" + companyName + "' Group/Company should selected: PASSED");
		
		driver.findElement(By.id("frmChooseCmp_0")).click();
		LOG.info("Click 'Next' Button");	
		
		return new ClaimDirectorCustomizerPage();
	}
	
	
	public OnlineUtilizationReportPage navigateToOnlineUtilReportPage(String companyName){
		OnlineUtilizationReport.click();		
		LOG.info("Click 'Online Utilization Report' Link on Account Management Page");
		
		assertTrue(OnlineUtilizationReport.isDisplayed());
		assertTrue(BrowserDriver.verifyTextPresent("ISO ClaimSearch Management Reports"));
		LOG.info("Verification: 'ISO ClaimSearch Management Reports' Message should be displayed: PASSED");
		
		OnlineUtilizationReport.click();
		LOG.info("Click 'Online Utilization Report' Link on 'ISO ClaimSearch Management Reports' Page");
		
		assertTrue(BrowserDriver.verifyTextPresent("Please choose the group/company you wish to view"));
		LOG.info("Verification: 'Please choose the group/company you wish to view' Message should be displayed: PASSED");

		new Select(driver.findElement(By.name("selGrp"))).selectByVisibleText(companyName);
		LOG.info("Verification: '" + companyName + "' Group/Company should selected: PASSED");
		
		driver.findElement(By.id("frmChooseCmp_0")).click();
		LOG.info("Click 'Next' Button");	
		
		return new OnlineUtilizationReportPage();
		
	}
	
	public IntegratedStatisticsReportPage navigateToIntegratedStatisticsReportPage(String companyName){
		IntegratedStatisticsReport.click();		
		LOG.info("Click 'Integrated Statistics Report' Link on Account Management Page");
		
		assertTrue(OnlineUtilizationReport.isDisplayed());
		assertTrue(BrowserDriver.verifyTextPresent("ISO ClaimSearch Management Reports"));
		LOG.info("Verification: 'ISO ClaimSearch Management Reports' Message should be displayed: PASSED");
		
		IntegratedStatisticsReport.click();
		LOG.info("Click 'Integrated Statistics Report' Link on 'ISO ClaimSearch Management Reports' Page");
		assertTrue(BrowserDriver.verifyTextPresent("Please choose the group/company you wish to view"));
		LOG.info("Verification: 'Please choose the group/company you wish to view' Message should be displayed: PASSED");

		new Select(driver.findElement(By.name("selGrp"))).selectByVisibleText(companyName);
		LOG.info("Verification: '" + companyName + "' Group/Company should selected: PASSED");
		
		driver.findElement(By.id("frmChooseCmp_0")).click();
		LOG.info("Click 'Next' Button");	
		
		return new IntegratedStatisticsReportPage();
		
	}
	
	
}
