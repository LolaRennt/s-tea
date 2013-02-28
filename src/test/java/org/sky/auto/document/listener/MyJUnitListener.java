package org.sky.auto.document.listener;

import org.junit.runner.Description;
import org.sky.auto.runner.listener.AutoRunnerJUnitListener;



public class MyJUnitListener extends AutoRunnerJUnitListener{
	@Override
	public void testStarted(Description description) throws Exception {
		System.out.println(description.getMethodName()+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~方法开始喽！~~");
	}
}
