package org.sky.auto.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sky.auto.base.AutoBase;

public class AjaxElement extends SElement{
	private SElement se;
	public AjaxElement(final WebElement we){
		WebDriverWait wait = new WebDriverWait(AutoBase.driver(),30);
		this.se=wait.until(new ExpectedCondition<SElement>() {
			public SElement apply(WebDriver driver){
				driver = AutoBase.driver();
				se= AutoBase.sElement();
				se.setElement(we);
				return se;
			}
		});
	}

	public AjaxElement(SElement se){
		this(se.getElement());
	}
	
	@Override
	public void sendKeys(String text) {
		se.sendKeys(text);
	}
	
	
	@Override
	public void click() {
		se.click();
	}
	
	
	
	
	
}
