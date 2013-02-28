package org.sky.auto.normal.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class it168NorTest {
	@Test
	public void testNor168(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.it168.com");
		Actions action = new Actions(driver);
		WebElement link = driver.findElement(By.id("chanpin")).findElements(By.tagName("a")).get(0);
		action.moveToElement(link);
		link.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
