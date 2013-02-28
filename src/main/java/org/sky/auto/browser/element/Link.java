package org.sky.auto.browser.element;

import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;

public class Link extends PageContorl{
	public Link(IBrowser browser) {
		super(browser);
	}
	
	public Link(IBrowser browser,WebElement element) {
		super(browser,element);
	}
	public String getHref(){
		return this.getAttribute("href");
	}
	
	
}
