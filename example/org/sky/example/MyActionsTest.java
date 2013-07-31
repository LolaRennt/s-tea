package org.sky.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;

import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.runner.JUnitBaseRunner;


@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads=1)
public class MyActionsTest {
	
	@Test
	public void testListener(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.sElement().node("#kw").sendKeys("北京");
		AutoBase.sElement().node("#su").click();
		AutoBase.closeAllWindow();
	}
	
}
