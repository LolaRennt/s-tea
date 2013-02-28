package org.sky.auto.test;

import org.sky.auto.base.AutoBase;
import org.sky.auto.base.TestNGBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.testpage.BaiduPage;
import org.sky.auto.window.Window;
import org.testng.annotations.Test;

public class NewTest extends TestNGBase{
//	@Test
//	public void testBaidu(){
//		Window.open(Browser.Firefox, "http://www.baidu.com");
//		AutoBase.setElementWaitTime(10);
//		AutoBase.textField("百度首页-搜索框").sendKeys("北京");
//		AutoBase.button("百度首页-搜索按钮").click();
//	}
	
	
//	@Test
//	public void testMyNewPage(){
//		Window.open("http://www.baidu.com");
//		AutoBase.setElementWaitTime(10);
//		AutoBase.textField("百度首页-搜索框").sendKeys("北京");
//		AutoBase.button("百度首页-搜索按钮").click();
//	}
	
	@Test
	public void testBaidu(){
		Window.open(Browser.Firefox,"http://www.baidu.com");
		AutoBase.page().load(BaiduPage.class).search("北京");
		Window.closeAllWindows();
	}
}
