package com.iso.claimsearch.test.ui.service.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.iso.claimsearch.test.ui.dao.TestBedDao;
import com.iso.claimsearch.test.ui.domain.ClaimSearchTestData;
import com.iso.claimsearch.test.ui.mapper.*;
import com.iso.claimsearch.test.ui.service.TestBedService;
import com.iso.claimsearch.test.ui.service.enums.DomainType;
import com.iso.claimsearch.test.ui.domain.EnvironmentCS;
import com.iso.claimsearch.test.ui.mapper.EnvironmentMapper;

public class TestBedServiceImpl  implements TestBedService 
{
	private TestBedDao dao;
	
	public void setDao(TestBedDao dao)
	{
		this.dao = dao;
	}
	
	
	public String findDomainByEnvironmentAndDomainType(String environment, DomainType domainType) throws Exception 
	{
		String[] parameters = new String [2];
		parameters[0] = environment.toUpperCase();
		parameters[1] = domainType.getCode();
		
		return dao.findDomainsByCriteria(TestBedService.DOMAIN_SQL, parameters, new DomainMapper()).get(0);
	}	
	
	public List<EnvironmentCS> findCSEnvironmentInfo(String environment) throws Exception {		
		String[] parameters = new String[1];
		parameters[0] = environment.toUpperCase();			
	
		return dao.findCSEnvByCriteria(TestBedService.URL_SQL, parameters, new EnvironmentMapper());
	}

	public List<ClaimSearchTestData> findCSTestsByEnvironmentAndTestCase(String environment, String testcase) throws Exception {
		String[] parameters = new String [2];
		parameters[0] = environment.toUpperCase();
		parameters[1] = testcase.toUpperCase();
		
		return dao.findTestsForClaimSearch(TestBedService.CLAIMSEARCH_SQL, parameters, new ClaimSearchTestDataMapper());

	}	
}