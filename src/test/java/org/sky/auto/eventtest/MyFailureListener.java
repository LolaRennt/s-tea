package org.sky.auto.eventtest;


import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.sky.auto.runner.listener.AutoRunnerJUnitListener;


public class MyFailureListener extends AutoRunnerJUnitListener{
	@Override
	public void testFailure(Failure failure) throws Exception {
		System.out.println("失败了！");
	}
	@Override
	public void testRunStarted(Description description) throws Exception {
		System.out.println("开始了！");
	}
}
