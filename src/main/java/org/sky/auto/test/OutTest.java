package org.sky.auto.test;

import org.junit.Test;
import org.sky.auto.robot.key.STeaKeywords;

public class OutTest {

	@Test
	public void test(){
		STeaKeywords sk = new STeaKeywords();
		sk.openBrowser("firefox", "http://www.baidu.com");
		sk.elementInput("百度首页-搜索框", "北京");
		sk.elementClick("百度首页-搜索按钮");
		sk.closeAllWindow();
	}
	
}
