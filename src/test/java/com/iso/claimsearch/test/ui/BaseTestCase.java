package com.iso.claimsearch.test.ui;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iso.claimsearch.test.navigator.impl.ClaimSearchNavigatorImpl;
import com.iso.claimsearch.test.ui.common.navigator.*;

@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional(propagation=Propagation.REQUIRED)

public abstract class BaseTestCase extends AbstractTransactionalJUnit4SpringContextTests
{
	public static final String ENVIRONMENT_KEY="TargetEnvironment";
	private ClaimSearchNavigator navigator;
	private List<String> sqlScripts;
	private String environment;
	
	public void setSqlScripts(List<String> sqlScripts)
	{
		this.sqlScripts = sqlScripts;		
	}
	
	@Before
	public void start() 
	{		
		startBrowser();	
		loadTestBed();
			
		// Retrieves from the pom.xml file
		//<systemPropertyVariables>		
		if (System.getProperty(BaseTestCase.ENVIRONMENT_KEY) != null) 
		{
			environment = System.getProperty(BaseTestCase.ENVIRONMENT_KEY);
		} 
		else 
		{
			environment = "T";
		}
	}
	
	public void startBrowser() 
	{		
		navigator = new ClaimSearchNavigatorImpl();
		navigator.start();
		
	}
	
	//Execute the SQL scripts - Load the test data based on the environment
	public void loadTestBed()
	{		
		//TODO: Autowiring: Temporarily manually wiring this bean because autowiring is not working.
		sqlScripts = (List<String>) applicationContext.getBean("sqlScripts");
		
		for (String sqlScript : sqlScripts) 
		{
			executeSqlScript(sqlScript, true);
		}	
	}

	@After
	public void stop() 
	{
		navigator.stop();
	}
	
	public ClaimSearchNavigator getNavigator()
	{
		return navigator;
	}
	
	public String getEnvironment()
	{		
		return environment;
	}
}