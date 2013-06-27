package org.sky.example;

import org.databene.benerator.anno.Source;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;

import com.github.lmm.runner.JUnitBaseRunner;

/**一个简单的例子，里面有元素的定义方式*/
@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads=2)
public class FirstCase {
	
	/**s-tea基于JUnit运行，如果想多线程或者参数化运行，需要用stea的专用运行器，BaseJUnitAutoRunner这个运行器.*/
	@Test
	public void open(){
		AutoBase.open(Browser.Firefox,"http://www.baidu.com");
		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
		AutoBase.sElement("百度首页-搜索按钮").click();
		AutoBase.closeAllWindow();
	}
	
	//通过class级别的注解来开启默认的运行器和设置线程数
	/**这个case演示了参数化的使用，test.xls文件放在项目的根目录下*/
	@Test
	@Source("test.xls")
	public void threadtest(String text){
		AutoBase.open(Browser.Firefox,"http://www.baidu.com");
		AutoBase.sElement("百度首页-搜索框").sendKeys(text);
		AutoBase.sElement("百度首页-搜索按钮").click();
		AutoBase.closeAllWindow();
	}
	
	
	
	
	
	
	
	
}
