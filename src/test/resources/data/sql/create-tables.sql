DROP table dbo.domain IF EXISTS CASCADE;
DROP table dbo.claimSearchEnv IF EXISTS CASCADE;
DROP table dbo.testClaimSearch IF EXISTS CASCADE;

CREATE TABLE dbo.domain (environment char(1), domaintype char(1), domain VARCHAR(500));

CREATE TABLE dbo.claimSearchEnv(environment char(3), url VARCHAR(500), uid VARCHAR(500), pwd VARCHAR(500));

CREATE TABLE dbo.testClaimSearch
(
environment char(3) DEFAULT '',
testcase varchar(50) DEFAULT '',
customergrpnumber varchar(50) DEFAULT '',
invoicenumber varchar(50) DEFAULT '',
gcompany varchar(100) DEFAULT '',
statsFor varchar(50) DEFAULT '',
totals varchar(50) DEFAULT '',
category varchar(50) DEFAULT '',
business varchar(50) DEFAULT '',
officeCode varchar(50) DEFAULT '',
claimNumber varchar(50) DEFAULT '',
dateOfLoss varchar(50) DEFAULT '',
policyNumber varchar(50) DEFAULT '',
policyType varchar(50) DEFAULT '',
address varchar(50) DEFAULT '',
city varchar(50) DEFAULT '',
state varchar(50) DEFAULT '',
lossDescription varchar(50) DEFAULT '',
physicalRiskAddress varchar(50) DEFAULT '',
physicalRiskCity varchar(50) DEFAULT '',
physicalRiskState varchar(50) DEFAULT '',
rreCode varchar(50) DEFAULT '',
lastName varchar(50) DEFAULT '',
firstName varchar(50) DEFAULT '',
involvedAddress varchar(50) DEFAULT '',
involvedCity varchar(50) DEFAULT '',
involvedState varchar(50) DEFAULT '',
dob varchar(50) DEFAULT '',
ssn varchar(50) DEFAULT '',
homePhone varchar(50) DEFAULT '',
occupation varchar(50) DEFAULT '',
allegedDamage varchar(50) DEFAULT '',
icd varchar(50) DEFAULT '',
genericName varchar(50) DEFAULT '',
brandName varchar(50) DEFAULT '',
manufacturer varchar(50) DEFAULT '',
allegedHarm varchar(50) DEFAULT '',
cmsDate varchar(50) DEFAULT '',
stateOfVenue varchar(50) DEFAULT '',
noFaultInsuranceLimitCent varchar(50) DEFAULT '',
noFaultInsuranceLimitDollar varchar(50) DEFAULT '',
lossType varchar(50) DEFAULT '',
claimStatus varchar(50) DEFAULT ''
);

