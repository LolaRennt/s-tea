package org.sky.auto.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sky.auto.base.AutoBase;

public class Button extends SElement{
	private WebElement element;
	public Button(){
		this.setElement(new RemoteWebElement());
	}
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
	
	public Button(By by){
		this.element=AutoBase.driver().findElement(by);
	}
	
	public Button(WebElement element){
		this.element=element;
	}
	
}
