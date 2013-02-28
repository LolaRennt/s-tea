package org.sky.auto.xml;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class XmlToJavaTools {
	static Logger logger = Logger.getLogger(XmlToJavaTools.class);
	
	
	public Map<By,Integer> by(String by,String selector,int index) throws Exception{
		Map<By,Integer> byMap= new HashMap<By,Integer>();
		By b=null;
		if(by.equals("id")){
			b = By.id(selector);
			byMap.put(b, index);
		}else if(by.equals("css")){
			b = By.cssSelector(selector);
			byMap.put(b, index);
		}else if(by.equals("tagName")){
			b = By.tagName(selector);
			byMap.put(b, index);
		}else if(by.equals("class")){
			b=By.className(selector);
			byMap.put(b, index);
		}else if(by.equals("xpath")){
			b=By.xpath(selector);
			byMap.put(b, index);
		}else if(by.equals("linkText")){
			b=By.linkText(selector);
			byMap.put(b, index);
		}else if(by.equals("name")){
			b=By.name(selector);
			byMap.put(b, index);
		}else if(by.equals("partialLinkText")){
			b=By.partialLinkText(selector);
			byMap.put(b, index);
		}else{
			logger.error(by+"这种定位方式没有被找到！");
			throw new Exception("["+by+"]这种定位方式没有被找到！");
		}
		return byMap;
	}
	
	public By locator(String by,String selector) throws Exception{	
		By b=null;
		if(by.equals("id")){
			b = By.id(selector);
		}else if(by.equals("css")){
			b = By.cssSelector(selector);
		}else if(by.equals("tagName")){
			b = By.tagName(selector);
		}else if(by.equals("class")){
			b=By.className(selector);
		}else if(by.equals("xpath")){
			b=By.xpath(selector);
		}else if(by.equals("linkText")){
			b=By.linkText(selector);
		}else if(by.equals("name")){
			b=By.name(selector);
		}else if(by.equals("partialLinkText")){
			b=By.partialLinkText(selector);
		}else{
			throw new Exception("["+by+"]这种定位方式没有被找到！");
		}
		return b;
	}
}
