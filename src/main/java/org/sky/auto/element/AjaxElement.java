package org.sky.auto.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sky.auto.base.AutoBase;

public class AjaxElement extends SElement{
	private WebElement webelement;
	public AjaxElement(final WebElement we){
		WebDriverWait wait = new WebDriverWait(AutoBase.driver(),30);
		this.setElement(wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver){
				return we;
			}
		}));
	}
	
	
	public AjaxElement(final SElement se){
		WebDriverWait wait = new WebDriverWait(AutoBase.driver(),30);
		this.setElement(wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver){
				return se.getElement();
			}
		}));
	}
	
	public AjaxElement(final By by){
		WebDriverWait wait = new WebDriverWait(AutoBase.driver(),30);
		this.setElement(wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver){
				driver=AutoBase.driver();
				return driver.findElement(by);
			}
		}));
	}
	
	public WebElement getElement() {
		return webelement;
	}
	public void setElement(WebElement we) {
		this.webelement = we;
	}	
}
