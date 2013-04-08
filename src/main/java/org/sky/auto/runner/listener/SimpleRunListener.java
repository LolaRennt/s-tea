package org.sky.auto.runner.listener;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.sky.auto.anno.RunListenerRegister;
import org.sky.auto.base.AutoBase;

@RunListenerRegister
public class SimpleRunListener extends RunListener{

	
	
	@Override
	public void testFinished(Description description) throws Exception {
		if(!AutoBase.isClose_Status()){
			AutoBase.closeAllWindow();
		}
	}
}



