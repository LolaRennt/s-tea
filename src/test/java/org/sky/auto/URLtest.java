package org.sky.auto;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.runner.BaseJUnitAutoRunner;
import org.sky.auto.window.Window;

@RunWith(BaseJUnitAutoRunner.class)
@ThreadRunner(threads=1)
public class URLtest {

	@Test
	public void url(){
		try {
			URL url = new URL("http://www.baidu.com");
			System.out.println(url.getProtocol());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void htmlTest(){
		AutoBase.open(Browser.HtmlUnit, "http://www.baidu.com");
		Window.maxWindow();
		AutoBase.closeAllWindow();
	}
	
}
