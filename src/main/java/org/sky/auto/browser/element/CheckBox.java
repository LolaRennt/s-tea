package org.sky.auto.browser.element;


import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;

public class CheckBox extends PageContorl{
	public CheckBox(IBrowser browser) {
		super(browser);
	}
	
	public CheckBox(IBrowser browser,WebElement element) {
		super(browser,element);
	}
	/**
	 * 检验这个checkBox多选框是否被选中
	 * @return true被选中
	 * 	       false没有被选中
	 * */
	public boolean isChecked(){
		if(isExist()){
			return getLocatorValue().isSelected();
		}else{
			logger.error("元素不存在，校验失败！");
		}
		return false;
		
	}
	
	/**设置多选框的状态，true是选中，false是取消选中
	 * @param status 设置选中状态
	 * */
	public void setStatus(boolean status){
		if(isExist()){
			if(getLocatorValue().isSelected()!=status){
				getLocatorValue().click();
			}
		}else{
			logger.error("没有找到元素，设定值失败！");
		}
		
	}
	
	
	
}
