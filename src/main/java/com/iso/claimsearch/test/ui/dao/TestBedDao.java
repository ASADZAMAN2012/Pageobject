package com.iso.claimsearch.test.ui.dao;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import com.iso.claimsearch.test.ui.domain.*;



public interface TestBedDao 
{	
	List<String> findDomainsByCriteria(String sql, Object[] parameters, RowMapper<String> rowMapper) throws Exception;
	List<EnvironmentCS> findCSEnvByCriteria(String sql, Object[] parameters, RowMapper<EnvironmentCS> rowMapper) throws Exception;
	List<ClaimSearchTestData> findTestsForClaimSearch(String sql, Object[] parameters, RowMapper<ClaimSearchTestData> rowMapper) throws Exception;
}