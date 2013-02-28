package org.sky.auto.pagetest;

import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.testng.annotations.Test;

public class Test168page {
//	@Test
//	public void test168Page(){
//		AutoBase.open(Browser.Firefox, "http://www.it168.com");
//		AutoBase.page().init(IT168Page.class).enterPB();
//		AutoBase.closeAllWindow();
//	}
	
	@Test
	public void test168(){
		AutoBase.open(Browser.Firefox,"http://www.it168.com");
		//System.out.println(AutoBase.sElement("笔记本浮层").getText());
		AutoBase.moveToElement(AutoBase.element("笔记本浮层"));
		//AutoBase.sElement("笔记本浮层").mouseOver();
		AutoBase.element("笔记本链接").click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AutoBase.closeAllWindow();
	}
}
