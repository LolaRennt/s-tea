package org.sky.auto.browser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SimplePlatform {
	private DesiredCapabilities dc;
	public SimplePlatform(){
		this.setDesiredCapabilities(new DesiredCapabilities());
	}
	public DesiredCapabilities getDesiredCapabilities() {
		return dc;
	}
	public void setDesiredCapabilities(DesiredCapabilities dc) {
		this.dc = dc;
	}
	
	public static DesiredCapabilities firefox(){
		return DesiredCapabilities.firefox();
	}
	
	public static DesiredCapabilities opera(){
		return DesiredCapabilities.opera();
	}
	
	public static DesiredCapabilities htmlUnit(){
		return DesiredCapabilities.htmlUnit();
	}
	
	public static DesiredCapabilities ie(){
		return DesiredCapabilities.internetExplorer();
	}
	
	public void setBrowserName(String browserName){
		dc.setBrowserName(browserName);
	}
	
	public void setCapability(String capabilityName,boolean value){
		dc.setCapability(capabilityName, value);
	}
	
	public void setCapability(String key,Object value){
		dc.setCapability(key, value);
	}
	
	public void setCapability(String capabilityName,Platform value){
		dc.setCapability(capabilityName, value);
	}
	
	public void setCapability(String capabilityName,String value){
		dc.setCapability(capabilityName, value);
	}
	
	public void setPlatform(Platform platform){
		dc.setPlatform(platform);
	}
	
	public void setVersion(String version){
		dc.setVersion(version);
	}
	
	public boolean isJsEnable(){
		return dc.isJavascriptEnabled();
	}
	
	public void setJsEnable(boolean enable){
		dc.setJavascriptEnabled(enable);
	}
	
	public String toString(){
		return dc.toString();
	}
	
}
