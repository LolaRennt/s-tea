package org.sky.auto.test;

import org.junit.Test;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.report.StandardOutInfo;

public class OutTest {

	@Test
	public void test(){
		StandardOutInfo soi = new StandardOutInfo();
		soi.start();
		System.out.println("thanks!");
		System.out.println("yes！！！！！");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		System.out.println("拯救的人是你么??");
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.closeAllWindow();
		soi.write();
		//soi.stop();
		soi.clearStream();
	}
	
}
