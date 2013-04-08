package org.sky.auto.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;



public class MyRunListener{
	

	@Test
	public void frameTest(){
		AutoBase.open(Browser.HtmlUnit, "xvcvcvcx");
		Window.selectFrame(By.id(""));
		//Window.selectFrame("")
		Window.selectFrame(By.id(""));
		AutoBase.sElement().addLocator(By.xpath(""),2).childElement(By.className(""),3).childElement(By.className("")).click();
		Window.selectDefaultWindow();
	}
	
	
	
	
}
