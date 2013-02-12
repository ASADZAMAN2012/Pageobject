DELETE FROM dbo.domain;
DELETE FROM dbo.claimSearchEnv;
DELETE FROM dbo.testClaimSearch;

--######## Environment & Domains
-- Production
INSERT INTO dbo.domain (environment, domaintype, domain) values ('P', 'I', 'https://claimsearch.iso.com');
INSERT INTO dbo.domain (environment, domaintype, domain) values ('P', 'A', 'https://claimsearch.iso.com');

-- Acceptance
INSERT INTO dbo.domain (environment, domaintype, domain) values ('A', 'I', 'https://claimsearchtest.iso.com');
INSERT INTO dbo.domain (environment, domaintype, domain) values ('A', 'A', 'https://claimsearchtest.iso.com');

-- Test/Development
INSERT INTO dbo.domain (environment, domaintype, domain) values ('T', 'I', 'https://claimsearchdev.iso.com');
INSERT INTO dbo.domain (environment, domaintype, domain) values ('T', 'A', 'https://claimsearchdev.iso.com');

--######## URL Info
INSERT INTO dbo.claimSearchEnv(environment,url,uid,pwd) values('T','https://claimsearchdev.iso.com/','QFFAD','qatest01');
INSERT INTO dbo.claimSearchEnv(environment,url,uid,pwd) values('A','https://claimsearchtest.iso.com/','QFFAD','qatest01');
INSERT INTO dbo.claimSearchEnv(environment,url,uid,pwd) values('P','https://claimsearch.iso.com/','QFFAD','qatest01');

--######## TEST DATA - ACCEPTANCE ENVIRONMENT
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,invoicenumber) values ('A','CSBILLINGDETAILS','D441','IT00039457');
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,invoicenumber) values ('A','CSDECISIONNET','A011','PR00000383');
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,gcompany) values ('A','CSEXECUTIVEANALYSISREP','A007','A007 - ALLSTATE INSURANCE COMPANY (GRP)');
INSERT INTO dbo.testClaimSearch(environment,testcase,gcompany,category,business) values ('A','CSCLAIMDIRECTOR','A007 - ALLSTATE INSURANCE COMPANY','General','ABC, INC.');
INSERT INTO dbo.testClaimSearch(environment,testcase,officeCode,claimNumber,dateOfLoss,policyNumber) values ('A','CSREPLACEMENTCLAIM','Z99500001','UFTEST','01202007','UFTEST');
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,gcompany) values ('A','CSONLINEUTILIZATIONREPORT','A007','A007 - ALLSTATE INSURANCE GROUP');
INSERT INTO dbo.testClaimSearch(environment,testcase,gcompany,statsFor,totals) values ('A','CSINTEGRATEDSTATSREP','A007 - ALLSTATE INSURANCE COMPANY (GRP)','Company','Year-to-Date')
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,invoicenumber,gcompany,statsFor,totals,category,business,officeCode,claimNumber,dateOfLoss,policyNumber,policyType,address,city,state,lossDescription,physicalRiskAddress,physicalRiskCity,physicalRiskState,rreCode,lastName,firstName,involvedAddress,involvedCity,involvedState,dob,ssn,homePhone,occupation,allegedDamage,icd,genericName,brandName,manufacturer,allegedHarm,cmsDate,stateOfVenue,noFaultInsuranceLimitCent,noFaultInsuranceLimitDollar,lossType,claimStatus) VALUES('A','CSINITIALCLAIMMEDICARE','','','','','','','','Q98700001','TESTCMSSCRIPT07','06012009','TESTCSSCRIPT','Personal Automobile','28 Broadway','New York','NY','Brain injury','545 Washington Blvd','Jersey City','NJ','987000000','Frost','Robert','91 Hudson Street','New York','NY','03151955','122743517','7183324648','Broker','Injury','91327','Generic Name','Brand Name','Manufacturer','No Harm','06012009','NY','25','30000','Medical Payments','Open');


--######## TEST DATA - PRODUCTION ENVIRONMENT
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,invoicenumber) values ('P','CSBILLINGDETAILS','D441','IT00039457');
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,invoicenumber) values ('P','CSDECISIONNET','A011','PR00088466');
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,gcompany) values ('P','CSEXECUTIVEANALYSISREP','A007','A007 - ALLSTATE INSURANCE COMPANY (GRP)');
INSERT INTO dbo.testClaimSearch(environment,testcase,gcompany,category,business) values ('P','CSCLAIMDIRECTOR','A103 - AFFIRMATIVE INSURANCE COMPANY','General','ABC, INC.');
INSERT INTO dbo.testClaimSearch(environment,testcase,officeCode,claimNumber,dateOfLoss,policyNumber) values ('P','CSREPLACEMENTCLAIM','Z99500001','TESTPRODSCRIPT01','08242007','TESTPRODSCRIPT01');
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,gcompany) values ('P','CSONLINEUTILIZATIONREPORT','A007','A007 - ALLSTATE INSURANCE GROUP');
INSERT INTO dbo.testClaimSearch(environment,testcase,gcompany,statsFor,totals) values ('P','CSINTEGRATEDSTATSREP','A007 - ALLSTATE INSURANCE COMPANY (GRP)','Company','Year-to-Date')
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,invoicenumber,gcompany,statsFor,totals,category,business,officeCode,claimNumber,dateOfLoss,policyNumber,policyType,address,city,state,lossDescription,physicalRiskAddress,physicalRiskCity,physicalRiskState,rreCode,lastName,firstName,involvedAddress,involvedCity,involvedState,dob,ssn,homePhone,occupation,allegedDamage,icd,genericName,brandName,manufacturer,allegedHarm,cmsDate,stateOfVenue,noFaultInsuranceLimitCent,noFaultInsuranceLimitDollar,lossType,claimStatus) VALUES('P','CSINITIALCLAIMMEDICARE','','','','','','','','Z99500001','TESTCMSSCRIPT07','06012009','TESTCMSSCRIPT02','Personal Automobile','28 Broadway','New York','NY','Brain injury','545 Washington Blvd','Jersey City','NJ','744444444','Frost','Robert','91 Hudson Street','New York','NY','03151955','122743517','7183324648','Broker','Injury','91327','Generic Name','Brand Name','Manufacturer','No Harm','06012009','NY','25','30000','Medical Payments','Open');


--######## TEST DATA - TEST ENVIRONMENT
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,invoicenumber) values ('T','CSBILLINGDETAILS','D441','IT00039457');
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,invoicenumber) values ('T','CSDECISIONNET','A011','PR00000383');
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,gcompany) values ('T','CSEXECUTIVEANALYSISREP','A007','A007 - ALLSTATE INSURANCE COMPANY (GRP)');
INSERT INTO dbo.testClaimSearch(environment,testcase,gcompany,category,business) values ('T','CSCLAIMDIRECTOR','A007 - ALLSTATE INSURANCE COMPANY','General','ABC, INC.');
INSERT INTO dbo.testClaimSearch(environment,testcase,officeCode,claimNumber,dateOfLoss,policyNumber) values ('T','CSREPLACEMENTCLAIM','Z99500001','UFTEST','01202007','UFTEST');
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,gcompany) values ('T','CSONLINEUTILIZATIONREPORT','A007','A007 - ALLSTATE INSURANCE GROUP');
INSERT INTO dbo.testClaimSearch(environment,testcase,gcompany,statsFor,totals) values ('T','CSINTEGRATEDSTATSREP','A007 - ALLSTATE INSURANCE COMPANY (GRP)','Company','Year-to-Date')
INSERT INTO dbo.testClaimSearch(environment,testcase,customergrpnumber,invoicenumber,gcompany,statsFor,totals,category,business,officeCode,claimNumber,dateOfLoss,policyNumber,policyType,address,city,state,lossDescription,physicalRiskAddress,physicalRiskCity,physicalRiskState,rreCode,lastName,firstName,involvedAddress,involvedCity,involvedState,dob,ssn,homePhone,occupation,allegedDamage,icd,genericName,brandName,manufacturer,allegedHarm,cmsDate,stateOfVenue,noFaultInsuranceLimitCent,noFaultInsuranceLimitDollar,lossType,claimStatus) VALUES('T','CSINITIALCLAIMMEDICARE','','','','','','','','Q98700001','TESTCMSSCRIPT07','06012009','TESTCSSCRIPT','Personal Automobile','28 Broadway','New York','NY','Brain injury','545 Washington Blvd','Jersey City','NJ','987000000','Frost','Robert','91 Hudson Street','New York','NY','03151955','122743517','7183324648','Broker','Injury','91327','Generic Name','Brand Name','Manufacturer','No Harm','06012009','NY','25','30000','Medical Payments','Open');

