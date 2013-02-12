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

public class CSClaimsReporting_TestCase extends BaseTestCase {
	public static final String ISONET_DOMAIN_KEY = "isonet.auth.domain";
	private static final Logger LOG = Logger.getLogger(CSClaimsReporting_TestCase.class);

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
	public void testCSReplacementClaim() throws Exception {
		String claimsearchDomain = service.findDomainByEnvironmentAndDomainType(getEnvironment(), DomainType.ISONET);
		
		// get the Login Details	
		List<EnvironmentCS> claimSearchLogin = service.findCSEnvironmentInfo(getEnvironment());
				
		List<ClaimSearchTestData> testDatas = service.findCSTestsByEnvironmentAndTestCase(getEnvironment(), "CSREPLACEMENTCLAIM");
			
		LOG.info("****************************************************");
		LOG.info("CLAIM SEARCH REPLACEMENT CLAIM - TEST STARTED");
		LOG.info("****************************************************");		
		
		for(ClaimSearchTestData testData : testDatas){
	    	loginPage = (CSLoginPage) getNavigator().open(new CSLoginPage(claimsearchDomain));				
			homePage = (CSHomePage) getNavigator().get(loginPage.LoginAs(claimSearchLogin.get(0).getUid(),claimSearchLogin.get(0).getPwd()));
			claimsReportingPage= (ClaimsReportingPage) getNavigator().get(homePage.clickClaimsReporting());
			claimSubmissionPage = (ClaimSubmissionPage) getNavigator().get(claimsReportingPage.clickClaimSubmission());
			claimsReportingPage= (ClaimsReportingPage) getNavigator().get(claimSubmissionPage.verifyExistingClaims(testData.getOfficeCode(), testData.getClaimNumber(),testData.getDateOfLoss(),testData.getPolicyNumber()));
			claimsReportingPage.verifyClaimSubmission();
			claimsReportingPage.logout();
		}
	}
	
	@Test
	public void testCSInitialClaimMedicareProject() throws Exception {
		String claimsearchDomain = service.findDomainByEnvironmentAndDomainType(getEnvironment(), DomainType.ISONET);
		
		// get the Login Details	
		List<EnvironmentCS> claimSearchLogin = service.findCSEnvironmentInfo(getEnvironment());
				
		List<ClaimSearchTestData> testDatas = service.findCSTestsByEnvironmentAndTestCase(getEnvironment(), "CSINITIALCLAIMMEDICARE");
			
		LOG.info("****************************************************");
		LOG.info("CLAIM SEARCH INITIAL CLAIM MEDICARE PROJECT - TEST STARTED");
		LOG.info("****************************************************");		
		
		for(ClaimSearchTestData testData : testDatas){
	    	loginPage = (CSLoginPage) getNavigator().open(new CSLoginPage(claimsearchDomain));				
			homePage = (CSHomePage) getNavigator().get(loginPage.LoginAs(claimSearchLogin.get(0).getUid(),claimSearchLogin.get(0).getPwd()));
			claimsReportingPage= (ClaimsReportingPage) getNavigator().get(homePage.clickClaimsReporting());
			claimSubmissionPage = (ClaimSubmissionPage) getNavigator().get(claimsReportingPage.clickClaimSubmission());
			String policyNumber = claimSubmissionPage.enterClaimDetails(testData.getOfficeCode(), testData.getClaimNumber(),testData.getDateOfLoss(),testData.getPolicyNumber());
			
			claimSubmissionBasicInfoPage = (ClaimSubmissionBasicInfoPage) getNavigator().get(claimSubmissionPage.navigateToBasicInformationPage());
			claimSubmissionBasicInfoPage.verifyClaimAndPolicyNumber(policyNumber,testData.getClaimNumber())
			.enterLocationOfLoss(testData.getPolicyType(),testData.getAddress(),testData.getCity(),testData.getState(),testData.getLossDescription())
			.enterPhysicalRiskInformation(testData.getPhysicalRiskAddress(),testData.getPhysicalRiskCity(),testData.getPhysicalRiskState())
			.enterMedicareReporting(testData.getRreCode());
			
			involvedPartyInformationPage = (InvolvedPartyInformationPage) getNavigator().get(claimSubmissionBasicInfoPage.clickNext());
			involvedPartyInformationPage.verifyClaimAndPolicyNumber(policyNumber,testData.getClaimNumber())
			.enterPartyInfo1(testData.getLastName(),testData.getFirstName(),testData.getInvolvedAddress(),testData.getInvolvedCity(),testData.getInvolvedState())
			.enterPartyInfo2(testData.getDob(),testData.getSsn(),testData.getHomePhone(),testData.getOccupation())
			.enterCoverageInformation()
			.enterGeneralCasualtyInformation(testData.getAllegedDamage());
			
			generalCasualtyInformationAdditionalPage = (GeneralCasualtyInformationAdditionalPage) getNavigator().get(involvedPartyInformationPage.navigateToGeneralCasualtyInformationAddPage());
			generalCasualtyInformationAdditionalPage.verifyClaimAndPolicyNumber(policyNumber,testData.getClaimNumber())
			.enterMedicareReportingInfo1(testData.getIcd(), testData.getGenericName(),testData.getBrandName(),testData.getManufacturer(),testData.getAllegedHarm())
			.enterMedicareReportingInfo2(testData.getCmsDate(), testData.getStateOfVenue(), testData.getNoFaultInsuranceLimitDollar(),testData.getNoFaultInsuranceLimitCent())
			.enterCasualtyCoverageInformation(testData.getLossType(),testData.getClaimStatus())
			.submitClaim();		
			
			generalCasualtyInformationAdditionalPage.logout();			
		}
	}
	
}