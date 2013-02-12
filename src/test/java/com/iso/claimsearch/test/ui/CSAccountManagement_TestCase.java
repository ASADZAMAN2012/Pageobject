package com.iso.claimsearch.test.ui;

import static org.junit.Assert.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.iso.claimsearch.test.pageobjects.*;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;
import com.iso.claimsearch.test.ui.domain.ClaimSearchTestData;

import com.iso.claimsearch.test.ui.service.TestBedService;
import com.iso.claimsearch.test.ui.service.enums.DomainType;
import com.iso.claimsearch.test.ui.domain.EnvironmentCS;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:definition/main/application-context.xml" })
@TestExecutionListeners

public class CSAccountManagement_TestCase extends BaseTestCase {
	public static final String ISONET_DOMAIN_KEY = "isonet.auth.domain";
	private static final Logger LOG = Logger.getLogger(CSAccountManagement_TestCase.class);

	private CSLoginPage loginPage;
	private CSHomePage homePage;
	private AccountManagementPage accountManagementPage;
	private CSBillingDetailPage csBillingDetailPage;
	private DecisionNetBillingDetailPage decisionNetBillingDetailPage;
	private ExecutiveAnalysisReportPage executiveAnalysisReportPage;
	private ClaimDirectorCustomizerPage claimDirectorCustomizerPage;
	private ClaimsReportingPage claimsReportingPage;
	private ClaimSubmissionPage claimSubmissionPage;
	private OnlineUtilizationReportPage onlineUtilizationReportPage;
	private IntegratedStatisticsReportPage integratedStatisticsReportPage;
	private ClaimSubmissionBasicInfoPage claimSubmissionBasicInfoPage;
	private GeneralCasualtyInformationAdditionalPage generalCasualtyInformationAdditionalPage;
	private InvolvedPartyInformationPage involvedPartyInformationPage;	
	
	@Autowired
	private TestBedService service;
	public void setTestBedService(TestBedService service) {
		this.service = service;
	}
	
	@Test
	public void testCSBillingDetailsRpt() throws Exception {
		String claimsearchDomain = service.findDomainByEnvironmentAndDomainType(getEnvironment(), DomainType.ISONET);
		
		// get the Login Details	
		List<EnvironmentCS> claimSearchLogin = service.findCSEnvironmentInfo(getEnvironment());
				
		List<ClaimSearchTestData> testDatas = service.findCSTestsByEnvironmentAndTestCase(getEnvironment(), "CSBILLINGDETAILS");
			
		LOG.info("****************************************************");
		LOG.info("CLAIM SEARCH BILLING DETAIL RPT - TEST STARTED");
		LOG.info("****************************************************");		
		
		for(ClaimSearchTestData testData : testDatas){
	    	loginPage = (CSLoginPage) getNavigator().open(new CSLoginPage(claimsearchDomain));				
			homePage = (CSHomePage) getNavigator().get(loginPage.LoginAs(claimSearchLogin.get(0).getUid(),claimSearchLogin.get(0).getPwd()));
			accountManagementPage= (AccountManagementPage) getNavigator().get(homePage.clickAccountManagement());
			csBillingDetailPage = (CSBillingDetailPage) getNavigator().get(accountManagementPage.navigateToISOClaimSearchBillingDetailPg());
			csBillingDetailPage.verifyBillingDetails(testData.getCustomergrpnumber(), testData.getInvoicenumber());	
			csBillingDetailPage.logout();
		}
	}
	
	@Test
	public void testCSDecisionNetBillingDetailsRpt() throws Exception {
		String claimsearchDomain = service.findDomainByEnvironmentAndDomainType(getEnvironment(), DomainType.ISONET);
		
		// get the Login Details	
		List<EnvironmentCS> claimSearchLogin = service.findCSEnvironmentInfo(getEnvironment());
				
		List<ClaimSearchTestData> testDatas = service.findCSTestsByEnvironmentAndTestCase(getEnvironment(), "CSDECISIONNET");
			
		LOG.info("****************************************************");
		LOG.info("CLAIM SEARCH DECISION NET BILLING DETAIL RPT - TEST STARTED");
		LOG.info("****************************************************");		
		
		for(ClaimSearchTestData testData : testDatas){
	    	loginPage = (CSLoginPage) getNavigator().open(new CSLoginPage(claimsearchDomain));				
			homePage = (CSHomePage) getNavigator().get(loginPage.LoginAs(claimSearchLogin.get(0).getUid(),claimSearchLogin.get(0).getPwd()));
			accountManagementPage= (AccountManagementPage) getNavigator().get(homePage.clickAccountManagement());
			decisionNetBillingDetailPage = (DecisionNetBillingDetailPage) getNavigator().get(accountManagementPage.navigateToDecisionNetBillingDetailPg());
			decisionNetBillingDetailPage.verifyBillingDetails(testData.getCustomergrpnumber(), testData.getInvoicenumber());	
			decisionNetBillingDetailPage.logout();
		}
	}
	

	@Test
	public void testCSExecutiveAnalysisReport() throws Exception {
		String claimsearchDomain = service.findDomainByEnvironmentAndDomainType(getEnvironment(), DomainType.ISONET);
		
		// get the Login Details	
		List<EnvironmentCS> claimSearchLogin = service.findCSEnvironmentInfo(getEnvironment());
				
		List<ClaimSearchTestData> testDatas = service.findCSTestsByEnvironmentAndTestCase(getEnvironment(), "CSEXECUTIVEANALYSISREP");
			
		LOG.info("****************************************************");
		LOG.info("CLAIM SEARCH EXECUTIVE ANALYSIS REPORT - TEST STARTED");
		LOG.info("****************************************************");		
		
		for(ClaimSearchTestData testData : testDatas){
	    	loginPage = (CSLoginPage) getNavigator().open(new CSLoginPage(claimsearchDomain));				
			homePage = (CSHomePage) getNavigator().get(loginPage.LoginAs(claimSearchLogin.get(0).getUid(),claimSearchLogin.get(0).getPwd()));
			accountManagementPage= (AccountManagementPage) getNavigator().get(homePage.clickAccountManagement());
			executiveAnalysisReportPage = (ExecutiveAnalysisReportPage) getNavigator().get(accountManagementPage.navigateToExecutiveAnalysisReport(testData.getGcompany()));
			executiveAnalysisReportPage.verifyExecutiveAnalysisReport(testData.getCustomergrpnumber());	
			executiveAnalysisReportPage.logout();
		}
	}
	
	@Test
	public void testCSClaimDirectorAcctMgmt() throws Exception {
		String claimsearchDomain = service.findDomainByEnvironmentAndDomainType(getEnvironment(), DomainType.ISONET);
		
		// get the Login Details	
		List<EnvironmentCS> claimSearchLogin = service.findCSEnvironmentInfo(getEnvironment());
				
		List<ClaimSearchTestData> testDatas = service.findCSTestsByEnvironmentAndTestCase(getEnvironment(), "CSCLAIMDIRECTOR");
			
		LOG.info("****************************************************");
		LOG.info("CLAIM SEARCH CLAIM DIRECTOR ACCOUNT MANAGEMENT - TEST STARTED");
		LOG.info("****************************************************");		
		
		for(ClaimSearchTestData testData : testDatas){
	    	loginPage = (CSLoginPage) getNavigator().open(new CSLoginPage(claimsearchDomain));				
			homePage = (CSHomePage) getNavigator().get(loginPage.LoginAs(claimSearchLogin.get(0).getUid(),claimSearchLogin.get(0).getPwd()));
			accountManagementPage= (AccountManagementPage) getNavigator().get(homePage.clickAccountManagement());
			claimDirectorCustomizerPage = (ClaimDirectorCustomizerPage) getNavigator().get(accountManagementPage.navigateToClaimDirectorPage(testData.getGcompany()));
			claimDirectorCustomizerPage.verifyClaimDirectorCustomizer(testData.getGcompany());	
			claimDirectorCustomizerPage.addAdvisory(testData.getGcompany(),testData.getCategory(),testData.getBusiness());
			claimDirectorCustomizerPage.logout();
		}
	}

	@Test
	public void testCSOnlineUtilizationReport() throws Exception {
		String claimsearchDomain = service.findDomainByEnvironmentAndDomainType(getEnvironment(), DomainType.ISONET);
		
		// get the Login Details	
		List<EnvironmentCS> claimSearchLogin = service.findCSEnvironmentInfo(getEnvironment());
				
		List<ClaimSearchTestData> testDatas = service.findCSTestsByEnvironmentAndTestCase(getEnvironment(), "CSONLINEUTILIZATIONREPORT");
			
		LOG.info("****************************************************");
		LOG.info("CLAIM SEARCH ONLINE UTILIZATION REPORT - TEST STARTED");
		LOG.info("****************************************************");		
		
		for(ClaimSearchTestData testData : testDatas){
			loginPage = (CSLoginPage) getNavigator().open(new CSLoginPage(claimsearchDomain));				
			homePage = (CSHomePage) getNavigator().get(loginPage.LoginAs(claimSearchLogin.get(0).getUid(),claimSearchLogin.get(0).getPwd()));
			accountManagementPage= (AccountManagementPage) getNavigator().get(homePage.clickAccountManagement());
			onlineUtilizationReportPage= (OnlineUtilizationReportPage) getNavigator().get(accountManagementPage.navigateToOnlineUtilReportPage(testData.getGcompany()));
			onlineUtilizationReportPage.verifyOnlineUtilizationReport(testData.getGcompany(),testData.getCustomergrpnumber());
			onlineUtilizationReportPage.logout();
		}
	}
	
	
	@Test
	public void testCSIntegratedStatisticsReport() throws Exception {
		String claimsearchDomain = service.findDomainByEnvironmentAndDomainType(getEnvironment(), DomainType.ISONET);
		
		// get the Login Details	
		List<EnvironmentCS> claimSearchLogin = service.findCSEnvironmentInfo(getEnvironment());
				
		List<ClaimSearchTestData> testDatas = service.findCSTestsByEnvironmentAndTestCase(getEnvironment(), "CSINTEGRATEDSTATSREP");
			
		LOG.info("****************************************************");
		LOG.info("CLAIM SEARCH INTEGRATED STATISTICS REPORT - TEST STARTED");
		LOG.info("****************************************************");		
		
		for(ClaimSearchTestData testData : testDatas){
			loginPage = (CSLoginPage) getNavigator().open(new CSLoginPage(claimsearchDomain));				
			homePage = (CSHomePage) getNavigator().get(loginPage.LoginAs(claimSearchLogin.get(0).getUid(),claimSearchLogin.get(0).getPwd()));
			accountManagementPage= (AccountManagementPage) getNavigator().get(homePage.clickAccountManagement());
			integratedStatisticsReportPage= (IntegratedStatisticsReportPage) getNavigator().get(accountManagementPage.navigateToIntegratedStatisticsReportPage(testData.getGcompany()));
			integratedStatisticsReportPage.verifyIntegratedStatisticsReport(testData.getGcompany(),testData.getStatsFor(),testData.getTotals());
			integratedStatisticsReportPage.logout();
			
		}
	}	
}