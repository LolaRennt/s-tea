package com.github.lmm.browser;

import java.net.URL;


public class RemoteBrowser extends BaseBrowser{
	public RemoteBrowser(Browser browser) {
		super(browser);
		setWebDriver(browser.remoteBrowser());
	}
	
	public RemoteBrowser(Browser browser,URL url){
		super(browser,url);
	}
}
