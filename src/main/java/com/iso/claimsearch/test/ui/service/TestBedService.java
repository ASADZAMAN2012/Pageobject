package com.iso.claimsearch.test.ui.service;

import java.util.List;
import com.iso.claimsearch.test.ui.domain.ClaimSearchTestData;
import com.iso.claimsearch.test.ui.service.enums.DomainType;
import com.iso.claimsearch.test.ui.domain.*;

public interface TestBedService 
{	
	String DOMAIN_SQL = "select domain from dbo.domain where environment = ? and domaintype = ?";
	String URL_SQL = "select * from dbo.claimSearchEnv where environment = ?";
	String CLAIMSEARCH_SQL ="select * from dbo.testClaimSearch where environment= ? and testcase = ?";
	
	String findDomainByEnvironmentAndDomainType(String environment, DomainType domainType) throws Exception;
	List<EnvironmentCS> findCSEnvironmentInfo(String environment) throws Exception;
	List<ClaimSearchTestData> findCSTestsByEnvironmentAndTestCase(String environment, String testcase) throws Exception;
}