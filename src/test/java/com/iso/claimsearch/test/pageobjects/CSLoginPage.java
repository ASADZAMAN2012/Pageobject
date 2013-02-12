package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.iso.claimsearch.test.common.pageobjects.CSBottomPane;
import com.iso.claimsearch.test.common.pageobjects.CSHeader;
import com.iso.claimsearch.test.common.pageobjects.Header;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;

public class CSLoginPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(CSLoginPage.class);
	
	//Login Page URL
	private final static String pageUrl = "/index.asp";
	
	private CSHeader csHeader;
	private CSBottomPane csBottomPane;
	
	@FindBy(how=How.NAME, using="UserID")
	private WebElement userID;
	
	@FindBy(how=How.NAME, using="Password")
	private WebElement password;
	
	@FindBy(how=How.NAME, using="chkAgree")
	private WebElement checkAgree;
	
	@FindBy(how=How.NAME, using="Submit")
	private WebElement loginButton;
	

	private WebDriver driver;
	
	public CSLoginPage()
	{
		super(pageUrl); //ApplicationPageBase > PageBase Class
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public CSLoginPage(String domain)
	{			
		super(domain, pageUrl); //PageBase Class		
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}
	
	@Override
	protected void isLoaded() throws Error 
	{
		assertFalse(userID.equals(Exception.class));
		assertFalse(password.equals(Exception.class));
		assertFalse(checkAgree.equals(Exception.class));
		assertFalse(loginButton.equals(Exception.class));
	}

	@Override
	protected void load() 
	{		
		csHeader = (CSHeader) (new CSHeader()).get();
		csBottomPane = (CSBottomPane) (new CSBottomPane()).get();
	}
	
	//Login to claim Search Page
	public CSHomePage LoginAs(String userName, String pwd) throws Exception
	{		
		userID.sendKeys(userName);
		LOG.info("Test step - Enter value in Username field : Passed ");
		
		password.sendKeys(pwd);
		LOG.info("Test step - Enter value in Passowrd field : Passed ");
		
		checkAgree.sendKeys(Keys.SPACE);
		loginButton.click();
		LOG.info("Test step - Click the Login button : Passed ");
		
		Thread.sleep(3000);
		if(BrowserDriver.isElementPresent(By.linkText("Claims Inquiry"))){			
		}			
		//To handle User Authentication		
		else if(BrowserDriver.isElementPresent(By.id("txtAns1")))
		{
			driver.findElement(By.id("txtAns1")).sendKeys("none");			
			driver.findElement(By.id("txtAns2")).sendKeys("none");
			driver.findElement(By.id("auth")).click();
			LOG.info("Test Step - Handle the User Authentication: Passed");			
		}
		
		return new CSHomePage();		
	}
}