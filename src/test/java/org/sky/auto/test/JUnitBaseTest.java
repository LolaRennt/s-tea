package org.sky.auto.test;

import org.junit.Test;
import org.sky.auto.base.AutoBase;
import org.sky.auto.base.JUnitBase;
import org.sky.auto.driver.Browser;

public class JUnitBaseTest extends JUnitBase{
	@Test
	public void testOne(){
		AutoBase.open(Browser.Firefox,"http://www.baidu.com");
		AutoBase.closeAllWindow();
	}
	@Test
	public void testTwo(){
		AutoBase.open(Browser.Firefox, "http://www.hao123.com");
		AutoBase.sleep(5000);
		//AutoBase.closeAllWindow();
	}
	
}
