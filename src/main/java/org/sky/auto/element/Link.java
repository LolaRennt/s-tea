package org.sky.auto.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sky.auto.base.AutoBase;

public class Link extends SElement{
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
	private WebElement element;
	public Link(){
		this.element=new RemoteWebElement();
	}
	
	public Link(By by){
		this.element=AutoBase.driver().findElement(by);
	}
	
	public Link(WebElement element){
		this.element=element;
	}
	
	public String getHref(){
		return this.getAttribute("href");
	}
	
	
}
