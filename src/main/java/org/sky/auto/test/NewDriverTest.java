package org.sky.auto.test;

import org.junit.Test;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;

public class NewDriverTest {
	@Test
	public void testDriver(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
		AutoBase.sElement("百度首页-搜索按钮").click();
		AutoBase.closeCurrentWindow();
	}
}
