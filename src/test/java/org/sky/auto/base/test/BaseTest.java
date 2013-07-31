package org.sky.auto.base.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.core.Auto;
import com.github.lmm.runner.JUnitBaseRunner;

@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads=1)
public class BaseTest {

	@Test
	public void hello(){
		Auto.require("firefox");
		Auto.open("http://www.baidu.com");
		Auto.closeAllWindows();
	}
}
