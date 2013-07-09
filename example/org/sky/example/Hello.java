package org.sky.example;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto;
import com.github.lmm.runner.JUnitBaseRunner;

@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads=1)
public class Hello {
	@Test
	public void hello(){
		Auto.require(Browser.FIREFOX);
		Auto.open("http://www.baidu.com");
		Auto.currentage().element("百度首页-搜索框").input("selenium");
		Auto.currentage().element("百度首页-搜索按钮").click();
		Auto.closeAllWindows();
	}
}
