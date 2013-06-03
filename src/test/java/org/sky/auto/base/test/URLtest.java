package org.sky.auto.base.test;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.runner.BaseJUnitAutoRunner;

@RunWith(BaseJUnitAutoRunner.class)
@ThreadRunner(threads=1)
public class URLtest {

	@Test
	public void test(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
		AutoBase.sElement("百度首页-搜索按钮").click();
		AutoBase.closeAllWindow();
	}
	 
}
