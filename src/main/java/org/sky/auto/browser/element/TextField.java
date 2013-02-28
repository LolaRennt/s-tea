package org.sky.auto.browser.element;

import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;

public class TextField extends PageContorl {
	public TextField(IBrowser browser) {
		super(browser);
	}
	
	public TextField(IBrowser browser,WebElement element) {
		super(browser,element);
	}
	
	public void setText(String text){
		if(isExist()){
			getLocatorValue().clear();
			getLocatorValue().sendKeys(text);
			logger.info("输入成功！");
		}else{
			logger.error("找不到元素！输入值失败！");
		}
	}
	
	public void clear(){
		if(isExist()){
			getLocatorValue().clear();
			logger.info("输入框清理值成功了！");
		}else{
			logger.error("查找元素失败！没有找到元素！");
		}
	}
	
	@Override
	public String getText(){
		String content=null;
		if(isExist()){
			content=getLocatorValue().getAttribute("value");
			logger.info("获得输入框文本内容成功");
		}else{
			logger.error("没有找到元素，获得值失败！");
		}
		return content;
	}
	
}
