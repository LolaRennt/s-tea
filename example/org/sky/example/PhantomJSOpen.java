package org.sky.example;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.github.lmm.annotation.Browsers;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto;

public class PhantomJSOpen {

	/**
	 * 在使用GhostDriver的时候我们需要先安装phantomjs,具体安装的方法去Phantomjs的官网查看和下载
	 * */
	public void defaultOpen(){
		PhantomJSDriver phantomjs = new PhantomJSDriver(DesiredCapabilities.phantomjs());
		phantomjs.close();
	}
	
	public void stea3open(){
		Auto.require(Browser.PhantomJS);
		Auto.closeAllWindows();
	}
	
	@Browsers({Browser.PhantomJS})
	public void stea3AtOpen(){
		Auto.closeAllWindows();
	}
	
}
