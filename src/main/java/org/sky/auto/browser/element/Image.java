package org.sky.auto.browser.element;

import org.openqa.selenium.WebElement;

import org.sky.auto.browser.IBrowser;

public class Image extends PageContorl{

	public Image(IBrowser browser) {
		super(browser);
	}
	
	public Image(IBrowser browser,WebElement element) {
		super(browser,element);
	}
	
	
}
