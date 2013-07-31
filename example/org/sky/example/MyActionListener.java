package org.sky.example;

import org.sky.auto.anno.Register;
import org.sky.auto.proxy.AutoRunnerListener;


@Register
public class MyActionListener extends AutoRunnerListener {

	@Override
	public void afterClickOn() {
		System.out.println("我点击了这个按钮");
	}
	
}
