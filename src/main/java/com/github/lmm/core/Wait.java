package com.github.lmm.core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
	private WebDriverWait wait;
	private WebDriver driver;
	public  Wait(WebDriver driver){
		this.driver=driver;
		this.wait=new WebDriverWait(driver,10);
	}
	public WebElement apply(final By by,final int index){
		return this.wait.until(new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver mydriver) {
				return mydriver.findElements(by).get(index);
			}
		});
	}
	
	public List<WebElement> applyList(final By by){
		return this.wait.until(new ExpectedCondition<List<WebElement>>() {

			@Override
			public List<WebElement> apply(WebDriver mydriver) {
				return mydriver.findElements(by);
			}
		});
	}
	public WebDriver getDriver() {
		return driver;
	}

	
}
