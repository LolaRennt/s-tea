package org.sky.auto.eventtest;


import org.sky.auto.driver.event.AutoRunnerListener;

public class MyListener extends AutoRunnerListener{
	@Override
	public void beforeClickOn() {
		System.out.println("这是可以的！！");
	}

	
}
