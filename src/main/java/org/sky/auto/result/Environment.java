package org.sky.auto.result;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.sky.auto.base.AutoBase;

public class Environment {
	
	public String getOS(){
		return ((RemoteWebDriver)AutoBase.driver()).getCapabilities().getPlatform().getPartOfOsName()[0];
	}
	
	public String getBrowser(){
		return ((RemoteWebDriver)AutoBase.driver()).getCapabilities().getBrowserName();
	}
	
	public String getBrowserVersion(){
		return ((RemoteWebDriver)AutoBase.driver()).getCapabilities().getVersion();
	}
}
