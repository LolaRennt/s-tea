package org.sky.auto.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sky.auto.base.AutoBase;

public class Image {
	private WebElement element;
	public Image(){
		this.element=new RemoteWebElement();
	}
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
	
	public Image(By by){
		this.element=AutoBase.driver().findElement(by);
	}
	
	public Image(WebElement element){
		this.element=element;
	}
	
	
	
	
	
}
