package com.iso.claimsearch.test.ui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import com.iso.claimsearch.test.ui.domain.ClaimSearchTestData;


public class ClaimSearchTestDataMapper implements RowMapper<ClaimSearchTestData> 
{
	public ClaimSearchTestData mapRow(ResultSet rs, int rowNum) throws SQLException 
	{		
		
		ClaimSearchTestData testData = new ClaimSearchTestData();
		
		testData.setEnvironment(StringUtils.trim(rs.getString("environment")));
		testData.setTestcase(StringUtils.trim(rs.getString("testcase")));
		testData.setCustomergrpnumber(StringUtils.trim(rs.getString("customergrpnumber")));
		testData.setInvoicenumber(StringUtils.trim(rs.getString("invoicenumber")));
		testData.setGcompany(StringUtils.trim(rs.getString("gcompany")));		
		testData.setStatsFor(StringUtils.trim(rs.getString("statsFor")));
		testData.setTotals(StringUtils.trim(rs.getString("totals")));		
		testData.setCategory(StringUtils.trim(rs.getString("category")));
		testData.setBusiness(StringUtils.trim(rs.getString("business")));
		testData.setOfficeCode(StringUtils.trim(rs.getString("officeCode")));
		testData.setClaimNumber(StringUtils.trim(rs.getString("claimNumber")));
		testData.setDateOfLoss(StringUtils.trim(rs.getString("dateOfLoss")));
		testData.setPolicyNumber(StringUtils.trim(rs.getString("policyNumber")));
		testData.setPolicyType(StringUtils.trim(rs.getString("policyType")));
		testData.setAddress(StringUtils.trim(rs.getString("address")));
		testData.setCity(StringUtils.trim(rs.getString("city")));
		testData.setState(StringUtils.trim(rs.getString("state")));
		testData.setLossDescription(StringUtils.trim(rs.getString("lossDescription")));
		testData.setPhysicalRiskAddress(StringUtils.trim(rs.getString("physicalRiskAddress")));
		testData.setPhysicalRiskCity(StringUtils.trim(rs.getString("physicalRiskCity")));
		testData.setPhysicalRiskState(StringUtils.trim(rs.getString("physicalRiskState")));
		testData.setRreCode(StringUtils.trim(rs.getString("rreCode")));
		testData.setLastName(StringUtils.trim(rs.getString("lastName")));
		testData.setFirstName(StringUtils.trim(rs.getString("firstName")));
		testData.setInvolvedAddress(StringUtils.trim(rs.getString("involvedAddress")));
		testData.setInvolvedCity(StringUtils.trim(rs.getString("involvedCity")));
		testData.setInvolvedState(StringUtils.trim(rs.getString("involvedState")));
		testData.setDob(StringUtils.trim(rs.getString("dob")));
		testData.setSsn(StringUtils.trim(rs.getString("ssn")));
		testData.setHomePhone(StringUtils.trim(rs.getString("homePhone")));
		testData.setOccupation(StringUtils.trim(rs.getString("occupation")));
		testData.setAllegedDamage(StringUtils.trim(rs.getString("allegedDamage")));
		testData.setIcd(StringUtils.trim(rs.getString("icd")));
		testData.setGenericName(StringUtils.trim(rs.getString("genericName")));
		testData.setBrandName(StringUtils.trim(rs.getString("brandName")));
		testData.setManufacturer(StringUtils.trim(rs.getString("manufacturer")));
		testData.setAllegedHarm(StringUtils.trim(rs.getString("allegedHarm")));
		testData.setCmsDate(StringUtils.trim(rs.getString("cmsDate")));
		testData.setStateOfVenue(StringUtils.trim(rs.getString("stateOfVenue")));
		testData.setNoFaultInsuranceLimitCent(StringUtils.trim(rs.getString("noFaultInsuranceLimitCent")));
		testData.setNoFaultInsuranceLimitDollar(StringUtils.trim(rs.getString("noFaultInsuranceLimitDollar")));
		testData.setLossType(StringUtils.trim(rs.getString("lossType")));
		testData.setClaimStatus(StringUtils.trim(rs.getString("claimStatus")));
		
		return testData;
	}
}