package org.sky.auto.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sky.auto.base.AutoBase;

public class TextField extends SElement {
	private WebElement element;
	public TextField(){
		this.element=new RemoteWebElement();
	}
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
	public TextField(By by){
		this.element=AutoBase.driver().findElement(by);
	}
	
	public TextField(WebElement element){
		this.element=element;
	}
	
	public void setText(String text){
		if(isExist()){
			getElement().clear();
			getElement().sendKeys(text);
			logger.info("输入成功！");
		}else{
			logger.error("找不到元素！输入值失败！");
		}
	}
	
	public void clear(){
		if(isExist()){
			getElement().clear();
			logger.info("输入框清理值成功了！");
		}else{
			logger.error("查找元素失败！没有找到元素！");
		}
	}
	
	@Override
	public String getText(){
		String content=null;
		if(isExist()){
			content=getElement().getAttribute("value");
			logger.info("获得输入框文本内容成功");
		}else{
			logger.error("没有找到元素，获得值失败！");
		}
		return content;
	}
	
}
