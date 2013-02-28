package org.sky.auto.base;

import org.openqa.selenium.By;

//import org.openqa.selenium.WebElement;

public class ElementBase {
	
	public static By locator(String by,String value) throws Exception{	
		By b=null;
		if(by.equals("id")){
			b = By.id(value);
		}else if(by.equals("css")){
			b = By.cssSelector(value);
		}else if(by.equals("tagName")){
			b = By.tagName(value);
		}else if(by.equals("class")){
			b=By.className(value);
		}else if(by.equals("xpath")){
			b=By.xpath(value);
		}else if(by.equals("linkText")){
			b=By.linkText(value);
		}else if(by.equals("name")){
			b=By.name(value);
		}else if(by.equals("partialLinkText")){
			b=By.partialLinkText(value);
		}else{
			throw new Exception("["+by+"]这种定位方式没有被找到！");
		}
		return b;
	}
}
