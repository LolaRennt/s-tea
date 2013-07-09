package org.sky.example;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;

import com.github.lmm.core.Auto;

public class MaxWindow {

	
	public void maxWindow(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("www.baidu.com");
		driver.quit();
	}
	
	public void steamaxWindow(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		Window.maxWindow();
		AutoBase.closeAllWindow();
	}
	
	public void stea3maxWindow(){
		Auto.require(com.github.lmm.browser.Browser.FIREFOX);
		Auto.open("http://www.baidu.com");
		Auto.maxWindow();
		Auto.closeAllWindows();
	}
	
	
	public void timeout(){
		WebDriver driver = new FirefoxDriver();
		//设置页面加载时间为30s
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//设置全局元素等待时间为30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.baidu.com");
		driver.quit();
	}
	
	public void steatimeout(){
		AutoBase.setDriver(Browser.Firefox);
		/**设置元素等待时间*/
		AutoBase.setElementWaitTime(30);
		/**设置页面加载时间为30s*/
		AutoBase.pageTimeOut(30);
		AutoBase.open("http://www.baidu.com");
		AutoBase.closeAllWindow();	
	}
	
	public void stea3timeout(){
		Auto.require(com.github.lmm.browser.Browser.FIREFOX);
		/**设置页面加载时间为30s*/
		Auto.pageLoadTimeout(30);
		/**查找元素默认的加载时间*/
		Auto.elementLoadTimeout(30);
		Auto.open("http://www.baidu.com");
		Auto.closeAllWindows();
	}
}
