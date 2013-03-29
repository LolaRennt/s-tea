package org.sky.auto.xml;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.sky.auto.exception.MyElementNotFoundException;

public class XmlToJavaTools {
	static Logger logger = Logger.getLogger(XmlToJavaTools.class);
	
	
	public Map<By,Integer> by(String by,String selector,int index){
		Map<By,Integer> byMap= new HashMap<By,Integer>();
		By b=null;
		if(by.toLowerCase().equals("id")){
			b = By.id(selector);
			byMap.put(b, index);
		}else if(by.toLowerCase().equals("css")){
			b = By.cssSelector(selector);
			byMap.put(b, index);
		}else if(by.toLowerCase().equals("tagname")){
			b = By.tagName(selector);
			byMap.put(b, index);
		}else if(by.toLowerCase().equals("class")){
			b=By.className(selector);
			byMap.put(b, index);
		}else if(by.toLowerCase().equals("xpath")){
			b=By.xpath(selector);
			byMap.put(b, index);
		}else if(by.toLowerCase().equals("linktext")){
			b=By.linkText(selector);
			byMap.put(b, index);
		}else if(by.toLowerCase().equals("name")){
			b=By.name(selector);
			byMap.put(b, index);
		}else if(by.toLowerCase().equals("partiallinktext")){
			b=By.partialLinkText(selector);
			byMap.put(b, index);
		}else{
			logger.error(by+"这种定位方式没有被找到！");
			throw new MyElementNotFoundException("["+by+"]这种定位方式没有被找到！");
		}
		return byMap;
	}
	
	public By locator(String by,String selector){	
		By b=null;
		if(by.toLowerCase().equals("id")){
			b = By.id(selector);
		}else if(by.toLowerCase().equals("css")){
			b = By.cssSelector(selector);
		}else if(by.toLowerCase().equals("tagname")){
			b = By.tagName(selector);
		}else if(by.toLowerCase().equals("class")){
			b=By.className(selector);
		}else if(by.toLowerCase().equals("xpath")){
			b=By.xpath(selector);
		}else if(by.toLowerCase().equals("linktext")){
			b=By.linkText(selector);
		}else if(by.toLowerCase().equals("name")){
			b=By.name(selector);
		}else if(by.toLowerCase().equals("partiallinktext")){
			b=By.partialLinkText(selector);
		}else{
			throw new MyElementNotFoundException("["+by+"]这种定位方式没有被找到！");
		}
		return b;
	}
}
