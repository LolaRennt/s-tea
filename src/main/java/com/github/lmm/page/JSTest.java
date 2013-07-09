package com.github.lmm.page;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;

import com.github.lmm.core.Auto;

public class JSTest {

	@Test
	public void runjs() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		((JavascriptExecutor)driver).executeScript("alert('hello,world');");
		driver.switchTo().alert().accept();
		driver.close();
	}	
	
	@Test
	public void stearunjs(){
		AutoBase.open(Browser.Firefox,"http://www.baidu.com");
		Window.runJS("alert('hello,world')");
		Window.dealAlert();
		AutoBase.closeAllWindow();
	}
	
	@Test
	public void stea3runjs(){
		Auto.require("firefox");
		Auto.open("http://www.baidu.com");
		Auto.runJavaScript("alert('hello,world')");
		Auto.dealAlert();
		Auto.closeAllWindows();
	}
}
