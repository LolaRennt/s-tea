package org.sky.auto.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sky.auto.base.AutoBase;
import org.sky.auto.exception.MyAutoException;
import org.sky.auto.report.RunTimeMethod;
import org.sky.auto.window.Window;

public class RichTextField extends SElement{
	private WebElement element;
	public RichTextField(WebElement element){
		this.element=element;
	}
	
	public RichTextField(By by){
		this.element=AutoBase.driver().findElement(by);
	}
	
	public RichTextField(){
		this.element=new RemoteWebElement();
	}

	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}
	
	
	/**在富文本框里面输入内容*/
	public void setText(String text){
		if(isExist()){
			String js="contentWindow.document.body.innerText=\'"+text+"\';";
			Window.runJS(js);
		}else{
			logger.error("["+RunTimeMethod.getName()+"]"+">>["+this.getId()+"]在富文本框内输入内容失败！");
			throw new MyAutoException("["+RunTimeMethod.getName()+"]"+"["+this.getId()+"]进行输入的时候出现错误，可能这个元素没有定位正确，元素不存在！");
		}
	}
	public void clearBodyText(){
		if(isExist()){
			String js="contentWindow.document.body.innerText=\'\';";
			Window.runJS(js);
			logger.info("["+RunTimeMethod.getName()+"]"+">>["+this.getId()+"]富文本框内容清理成功！");
		}else{
			logger.error("["+RunTimeMethod.getName()+"]"+"找到元素失败！清理失败！");
			throw new MyAutoException("["+RunTimeMethod.getName()+"]"+"["+this.getId()+"]进行清除操作的时候失败了，可能是这个元素定位失败！元素不存在！");
		}
	}
}
