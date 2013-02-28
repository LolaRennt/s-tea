package org.sky.auto.test;

import org.junit.Test;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;

public class ComoboxTest {
	@Test
	public void testSelect(){
		AutoBase.open(Browser.Firefox,"http://gpu.pcpop.com");
		Window.maxWindow();
		AutoBase.comoboBox("测评游戏").scroll();
		AutoBase.comoboBox("测评游戏").selectByVisiableText("天堂3.0");
		AutoBase.sleep(3000);
		AutoBase.comoboBox("测评模块").scroll();
		AutoBase.comoboBox("测评模式").selectByVisiableText("1680x1050 Extreme");
		AutoBase.sleep(3000);
		String text = AutoBase.sElement("测评显卡均价").getText();
		System.out.println(text);
		AutoBase.closeAllWindow();
		
	}
}
