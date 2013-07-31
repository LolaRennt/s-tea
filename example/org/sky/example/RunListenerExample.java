package org.sky.example;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import com.github.lmm.annotation.RunnerRegister;


/**通过注解就能够注册到运行器监听器*/
@RunnerRegister
public class RunListenerExample  extends RunListener{
	@Override
	public void testFailure(Failure failure) throws Exception {
		System.out.println("测试失败了！");
	}
}
