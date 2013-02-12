package com.iso.claimsearch.test.navigator.impl;

import java.net.URISyntaxException;

import com.iso.claimsearch.test.ui.common.navigator.ClaimSearchNavigator;
import com.iso.claimsearch.test.ui.common.core.PageBase;
import com.iso.claimsearch.test.ui.common.driver.BrowserDriver;


public class ClaimSearchNavigatorImpl implements ClaimSearchNavigator 
{
	public String getCurrentUrl()
    {
        return BrowserDriver.getUrl();
    }
	
	public void sleep(int t) throws InterruptedException
    {
        Thread.sleep(t);
    }

  
    public PageBase switchWindow(PageBase target, String windowName)
    {
        BrowserDriver.switchWindow(windowName);
        BrowserDriver.waitForPageToLoad();

        return target;
    }

    public void closeWindow(String windowName)
    {
        BrowserDriver.closeWindow(windowName);
    }

    public String getWindow(int index)
    {
    	return (String) BrowserDriver.getWindowHandles().toArray()[index];
    }

    public PageBase get(PageBase target) throws URISyntaxException {    	
		BrowserDriver.waitForPageToLoad();
		
		return target.get();
		
	}

	public PageBase open(PageBase target) throws URISyntaxException {		
		BrowserDriver.open(target.getPageUrl());
		
		BrowserDriver.waitForPageToLoad();
		
		
		return target;
		
	}

	public void start() {
		BrowserDriver.start();
		BrowserDriver.maximizeWindow();
		
	}

	public void stop() {
		
		BrowserDriver.stop();
	}	
}