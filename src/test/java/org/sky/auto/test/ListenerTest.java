package org.sky.auto.test;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.Author;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.runner.BaseJUnitAutoRunner;
import org.sky.auto.window.Window;
@RunWith(BaseJUnitAutoRunner.class)
@ThreadRunner(threads=2)
@Author(Name="王天庆")
public class ListenerTest {
	@Test
	public void ListenerByTest(){
		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.setElementWaitTime(2);
		//ProxyRunnerListener.register(new MyListener());
		Window.maxWindow();
		AutoBase.sElement("百度首页-搜索框").sendKeys("手机");
		AutoBase.sElement("百度首页-搜索按钮").click();
		AutoBase.sleep(2);
		AutoBase.closeAllWindow();
		//Assert.fail();
		
	}
	@Test
	public void ListenerTwo(){
		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.setElementWaitTime(2);
		//ProxyRunnerListener.register(new MyListener());
		Window.maxWindow();
		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
		AutoBase.sElement("百度首页-搜索按钮").click();
		AutoBase.sleep(2);
		AutoBase.closeAllWindow();
		//Assert.fail();
		
	}
}
