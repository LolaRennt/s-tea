package org.sky.auto.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sky.auto.base.AutoBase;
import org.sky.auto.exception.MyAutoException;
import org.sky.auto.report.RunTimeMethod;


public class CheckBox extends SElement{
	private WebElement element;
	public CheckBox(){
		this.setElement(new RemoteWebElement());
	}
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
	public CheckBox(By by){
		this.element=AutoBase.driver().findElement(by);
	}
	
	public CheckBox(WebElement element){
		this.element=element;
	}
	/**
	 * 检验这个checkBox多选框是否被选中
	 * @return true被选中
	 * 	       false没有被选中
	 * */
	public boolean isChecked(){
		if(isExist()){
			return getElement().isSelected();
		}else{
			logger.error("["+RunTimeMethod.getName()+"]"+"元素不存在，校验失败！");
			throw new MyAutoException("["+this.getId()+"]判断元素是否被选中的时候出现了错误，可能的原因是这个元素没有被找到！");
		}
		
		
	}
	
	/**设置多选框的状态，true是选中，false是取消选中
	 * @param status 设置选中状态
	 * */
	public void setStatus(boolean status){
		if(isExist()){
			if(getElement().isSelected()!=status){
				getElement().click();
			}
		}else{
			logger.error("["+RunTimeMethod.getName()+"]"+"没有找到元素，设定值失败！");
			throw new MyAutoException("["+this.getId()+"]设置状态的时候出现了错误，可能的原因是这个元素没有被找到！");
		}
		
	}
	
	
	
}
