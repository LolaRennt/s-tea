package org.sky.auto.browser.element;



import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;

public class Button extends PageContorl{
	public Button(IBrowser browser) {
		super(browser);
	}
	
	public Button(IBrowser browser,WebElement element) {
		super(browser,element);
	}
}
