package org.sky.example;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.lmm.annotation.Listener;
import com.github.lmm.annotation.Retry;
import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto;
import com.github.lmm.runner.JUnitBaseRunner;


@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads=1)
public class RetryTest {
	
	@Test
	@Retry(2)
	@Listener({RetryAction.class})
	public void retry(){
		Auto.require(Browser.FIREFOX);
		Auto.open("http://www.baidu.com");
		Auto.currentpage().element().addLocator("#kw").input("北京");
		Auto.currentpage().node("#su").click();
		Auto.closeAllWindows();
	}

}
