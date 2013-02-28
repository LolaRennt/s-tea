package org.sky.auto.browser.element;

import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;

public class RichTextField extends PageContorl{
	public RichTextField(IBrowser browser) {
		super(browser);
	}
	
	public RichTextField(IBrowser browser,WebElement element) {
		super(browser,element);
	}

	
	
	/**在富文本框里面输入内容*/
	public void setText(String text){
		if(isExist()){
			String js="contentWindow.document.body.innerText=\'"+text+"\';";
			getBrowser().runJavaScript(js);
		}else{
			logger.error("在富文本框内输入内容失败！");
		}
	}
	public void clearBodyText(){
		if(isExist()){
			String js="contentWindow.document.body.innerText=\'\';";
			getBrowser().runJavaScript(js);
			logger.info("富文本框内容清理成功！");
		}else{
			logger.error("找到元素失败！清理失败！");
		}
	}
}
