package org.sky.example;

import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;

import com.github.lmm.annotation.Browsers;
import com.github.lmm.core.Auto;
import com.github.lmm.core.Auto.Firefox;

public class FirefoxSteaOpen {
	/**这个里面的打开方法是s-tea1.0的使用方法*/
	public void steaOpen(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.closeAllWindow();
	}
	/**下面的这个方法只会打开浏览器和初始化浏览器的环境，并不会打开任何的网址*/
	public void steaDriverOpen(){
		AutoBase.setDriver(Browser.Firefox);
		AutoBase.closeAllWindow();
	}
	
//!_____-------------------------------s-tea3.0-----------------------------------_____!
	
	/**下面的演示是所有的s-tea3.0的打开Firefox的方法，打开别的浏览器的代码方式一样，通过一个枚举类型实现*/
	public void stea3open(){
		Auto.require(com.github.lmm.browser.Browser.FIREFOX);
		Auto.closeAllWindows();
	}
	
	/**注解的打开方式*/
	@Browsers({com.github.lmm.browser.Browser.FIREFOX})
	public void stea3AtOpen(){
		//加入注解的话，在进入方法体之前，s-tea3.0就会自动打开了浏览器，并且注解@Browsers支持同时使用多个浏览器，意味着
		//我们可以同时用多个浏览器来运行这个方法。
		Auto.closeAllWindows();
	}
	
	public void stea3FirefoxOpen(){
		Firefox.open("http://www.baidu.com");
		Firefox.closeAllWindows();
	}
}
