package org.sky.auto.browser.element;


import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;

public class RadioButton extends CheckBox{
	
	public RadioButton(IBrowser browser) {
		super(browser);
	}
	
	public RadioButton(IBrowser browser,WebElement element) {
		super(browser,element);
	}
	public boolean isChecked(){
		return super.isChecked();
	}
	
	public void setStatus(boolean status){
		super.setStatus(status);
	}
	
	
}
