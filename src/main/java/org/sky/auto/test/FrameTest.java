package org.sky.auto.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.runner.BaseJUnitAutoRunner;


@RunWith(BaseJUnitAutoRunner.class)
@ThreadRunner(threads=1)
public class FrameTest {
	
	
//	@Test
//	public void frametest(){
//		AutoBase.open(Browser.Firefox, "http://product.it168.com/list/b/03010377_1.shtml");
//		AutoBase.sleep(5);
//		AutoBase.sElement("百度-联想").click();
//		AutoBase.closeAllWindow();
//	}
	
	@Test
	public void baidu(){
		AutoBase.open(Browser.HtmlUnit, "http://www.baidu.com");
//		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
//		AutoBase.sElement("百度首页-搜索按钮").click();
		AutoBase.closeAllWindow();
	}
	
	
	
	
}
