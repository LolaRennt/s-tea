package org.sky.auto.browser.element;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.sky.auto.element.Locator;
import org.sky.auto.proxy.ProxyRunnerListener;



/**在2.0版本里面能够替代sElement元素的控件类*/
public abstract class Contorl {
	//private IBrowser  browser;
	static Logger logger = Logger.getLogger(Contorl.class);
	public abstract Contorl addLocator(By by);
	public abstract Contorl addLocator(WebElement we);
	public abstract WebElement getLocatorValue();
	public abstract void setLocatorValue(WebElement we);
	public abstract Contorl addLocator(Locator locator,String value);
	public abstract Contorl addLocator(Locator locator,String value,int index);
	public abstract Contorl addLocator(By by,int index);
	
	/**点击操作*/
	public void click(){
		ProxyRunnerListener.getDispatcher().beforeClickOn();
		if(isExist()){
			getLocatorValue().click();
			logger.info("点击成功！");
		}else{
			logger.error("没有找到元素，点击失败！");
		}
		ProxyRunnerListener.getDispatcher().afterClickOn();
		
		
	}
	protected boolean isExist() {
		if(getLocatorValue()==null){
			return false;
		}
		return true;
	}
	
	public void sendKeys(CharSequence keysToSend){
		ProxyRunnerListener.getDispatcher().beforeSendkeys();
		if(isExist()){
			getLocatorValue().sendKeys(keysToSend);
			logger.info("输入值"+keysToSend+"成功！");
		}else{
			logger.error("没有找到元素，点击失败！");
		}
		ProxyRunnerListener.getDispatcher().afterSendkeys();
	}
	public abstract void clear();
	public abstract void doubleClick();
	public abstract void dragAndDrop(Point p);
	public abstract void dragAndDrop(Contorl c);
	public abstract String getAttribute(String value);
	public abstract String getCssValue(String css);
	public abstract Point getLocation();
	public abstract int[] getSize();
	public abstract String getTagName();
	public abstract String getText();
	public abstract boolean isDisplay();
	public abstract boolean isEnable();
	public abstract boolean isSelected();
	public abstract void keyDown(Keys key);
	public abstract void keyUp(Keys key);
	public abstract void leftDown();
	public abstract void leftUp();
	public abstract void mouseOver();
	public abstract void scroll();
	
	
	
	
	
}
