package org.sky.auto.test;

import org.databene.benerator.anno.Source;
import org.sky.auto.base.AutoBase;
import org.sky.auto.base.TestNGRemoteBase;
import org.testng.annotations.Test;



public class JUnitRemoteBaseTest extends TestNGRemoteBase{
	@Test
	public void methodOne(){
		AutoBase.open("http://www.baidu.com");
		AutoBase.sleep(2000);
	}
	
	@Test(dataProvider="feeder")
	@Source("browser.xls")
	public void methodTwo(String text){
		System.out.println(text);
		AutoBase.open("http://www.hao123.com");
		AutoBase.sleep(3000);
	}
	
	@Test
	public void methodThree(){
		AutoBase.open("http://www.sohu.com");
	}
}
