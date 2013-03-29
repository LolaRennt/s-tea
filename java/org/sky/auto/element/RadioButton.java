package org.sky.auto.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sky.auto.base.AutoBase;

public class RadioButton extends CheckBox{
	private WebElement element;
	public RadioButton(){
		this.element=new RemoteWebElement();
	}
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
	
	public RadioButton(By by){
		this.element=AutoBase.driver().findElement(by);
	}
	
	public RadioButton(WebElement element){
		this.element=element;
	}
	
	public boolean isChecked(){
		return super.isChecked();
	}
	
	public void setStatus(boolean status){
		super.setStatus(status);
	}
	
	
}
