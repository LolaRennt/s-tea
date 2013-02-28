package org.sky.auto.test;

import org.databene.benerator.anno.Source;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.runner.BaseJUnitAutoRunner;



@RunWith(BaseJUnitAutoRunner.class)
@ThreadRunner(threads=2)
public class ParamtersTest {
	
	@Test
	@Source("test.xls")
	public void methodOne(String text){
		AutoBase.setLogStarted();
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.setElementWaitTime(20);
		AutoBase.element("百度首页-搜索框").sendKeys(text);
		AutoBase.element("百度首页-搜索按钮").click();
		AutoBase.closeAllWindow();
	}
}
