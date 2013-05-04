package org.sky.auto.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.AutoBase;

import org.sky.auto.driver.Browser;
import org.sky.auto.runner.BaseJUnitAutoRunner;




/**
 * Unit test for simple App.
 */
@RunWith(BaseJUnitAutoRunner.class)
@ThreadRunner(threads=2)
public class AppTest {
	@Test
	public void testSElement(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
		
		AutoBase.sElement("百度首页-搜索按钮").click();
		AutoBase.currentElement().click();
		AutoBase.closeAllWindow();
	}
//	@Test
//	public void testSElement1(){
//		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
//		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
//		AutoBase.sElement("百度首页-搜索按钮").click();
//		AutoBase.closeAllWindow();
//	}
//	@Test
//	public void testSElement2(){
//		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
//		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
//		AutoBase.sElement("百度首页-搜索按钮").click();
//		AutoBase.closeAllWindow();
//	}
//	@Test
//	public void testSElement3(){
//		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
//		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
//		AutoBase.sElement("百度首页-搜索按钮").click();
//		AutoBase.closeAllWindow();
//	}
	
	
	
  
}
