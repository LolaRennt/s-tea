package org.sky.auto.eventtest;

import org.junit.Test;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;

public class ListenerTest {
//	@Test
//	public void testListener(){
//		AutoBase.open(Browser.Firefox,"http://www.baidu.com");
//		AutoBase.getAutoDriver().register(new MyListener());
//		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
//		AutoBase.sElement("百度首页-搜索按钮").click();
//		AutoBase.closeAllWindow();
//	}
	
	@Test
	public void testAOP(){
		AutoBase.open(Browser.Firefox,"http://www.baidu.com");
		//ProxyRunnerListener.regsiter(new MyListener());
		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
		AutoBase.sElement("百度首页-搜索按钮").click();
		AutoBase.closeAllWindow();
	}
}
