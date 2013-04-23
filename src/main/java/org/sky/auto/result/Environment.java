package org.sky.auto.result;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sky.auto.base.AutoBase;

public class Environment {
	private String OS;
	private String browser;
	private String browserVersion;
	public String getOs() {
		return OS;
	}

	public void setOs(String os) {
		this.OS = os;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	
	public String getOS() {
		return OS;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public String getBrowser() {
		return browser;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public Environment(){	
		if(AutoBase.driver() instanceof HtmlUnitDriver){
			this.browser="HtmlUnit";
			this.browserVersion="";
			this.OS="";
		}else{
			RemoteWebDriver driver=(RemoteWebDriver) AutoBase.driver();
			this.OS=driver.getCapabilities().getPlatform().getPartOfOsName()[0];
			this.browser=driver.getCapabilities().getBrowserName();
			this.browserVersion=driver.getCapabilities().getVersion();
		}
	}
}
