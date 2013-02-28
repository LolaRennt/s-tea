package org.sky.auto.normal.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutoHomeSearchTest {

	
	@Test
	public void search(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.autohome.com.cn");
		//driver.findElement(By.className("input_text")).sendKeys("奥迪");
		driver.findElement(By.id("q")).sendKeys("奥迪");
		driver.findElement(By.className("input_button")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
