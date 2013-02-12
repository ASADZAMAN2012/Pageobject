package com.iso.claimsearch.test.pageobjects;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.iso.claimsearch.test.common.pageobjects.Footer;
import com.iso.claimsearch.test.common.pageobjects.Header;
import com.iso.claimsearch.test.ui.common.core.PageBase;

public abstract  class ApplicationPageBase extends PageBase
{
	private Header header;	
	private Footer footer;
	
	private static final Logger LOG = Logger.getLogger(ApplicationPageBase.class);

	public ApplicationPageBase(String pageUrl) 
	{		
		super(pageUrl);		
	}
	
	public ApplicationPageBase(String pageUrl, String domain) 
	{
		super(pageUrl, domain);		
	}
	
	@Override
	protected void load() 
	{
		header = (Header) (new Header()).get();		
		footer = (Footer) (new Footer()).get();
	}
	
	public void logout()
	{
		load();
		header.logout();
	}

	public void clearAndType(WebElement we, String text, String field)
	{
		if (!text.isEmpty() && !text.equals("")) {
			we.click();
			we.clear();
			we.sendKeys(text);
			assertEquals(we.getAttribute("value"), text);
			LOG.info("Entered \"" + text + "\" in the field ' " + field + " '");
		}
	}
}