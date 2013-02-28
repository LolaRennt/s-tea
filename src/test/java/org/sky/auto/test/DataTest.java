package org.sky.auto.test;

import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;
import org.testng.annotations.Test;

public class DataTest {
	
//	@Test(dataProvider="feeder")
//	@Source("test.xls")
//	public void testData(String name,int id){
//		
//		System.out.println("name--->"+name);
//		System.out.print("id--->"+id);
//	}
	
	@Test
	public void maxTest(){
		AutoBase.open(Browser.Firefox,"http://www.baidu.com");
		Window.maxWindow();
		try {
			Thread.sleep(5999);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Window.closeAllWindows();
	}

}
