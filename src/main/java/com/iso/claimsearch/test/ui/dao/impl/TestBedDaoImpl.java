package com.iso.claimsearch.test.ui.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iso.claimsearch.test.ui.dao.TestBedDao;
import com.iso.claimsearch.test.ui.domain.*;


public class TestBedDaoImpl extends JdbcDaoSupport implements TestBedDao 
{	
	public List<String> findDomainsByCriteria(String sql, Object[] parameters, RowMapper<String> rowMapper) throws Exception 
	{
		return getJdbcTemplate().query(sql, parameters, rowMapper);
	}
	
	public List<ClaimSearchTestData> findTestsForClaimSearch(String sql, Object[] parameters, RowMapper<ClaimSearchTestData> rowMapper)
			throws Exception {
		
		return getJdbcTemplate().query(sql, parameters, rowMapper);
	}
	
	public List<EnvironmentCS> findCSEnvByCriteria(String sql,Object[] parameters, RowMapper<EnvironmentCS> rowMapper)throws Exception 
	{			
		return getJdbcTemplate().query(sql, parameters, rowMapper);
	}	
	
}