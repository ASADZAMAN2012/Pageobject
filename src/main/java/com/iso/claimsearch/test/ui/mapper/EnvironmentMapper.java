package com.iso.claimsearch.test.ui.mapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.iso.claimsearch.test.ui.domain.*;

public class EnvironmentMapper implements RowMapper<EnvironmentCS> {
	
	public EnvironmentCS mapRow(ResultSet rs, int rowNum) throws SQLException 
	{	
		EnvironmentCS testData = new EnvironmentCS();		
		
		testData.setUid(StringUtils.trim(rs.getString("uid")));
		testData.setPwd(StringUtils.trim(rs.getString("pwd")));
		testData.setUrl(StringUtils.trim(rs.getString("url")));
		
		return testData;
	}
}
