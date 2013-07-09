package org.sky.example;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

public class HtmlUnit {

	/**选择模拟的浏览器的版本，并且设置可以使用js*/
	public void open(){
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
		driver.setJavascriptEnabled(true);
		driver.close();
	}
}
