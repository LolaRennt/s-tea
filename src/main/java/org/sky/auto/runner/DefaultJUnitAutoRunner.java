package org.sky.auto.runner;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.sky.auto.runner.listener.DefaultAutoRunnerJUnitListener;
import org.sky.auto.runner.listener.SimpleRunListener;

public class DefaultJUnitAutoRunner extends BaseJUnitAutoRunner{

	public DefaultJUnitAutoRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}
	
	@Override
	public void run(RunNotifier rn) {
		rn.addListener(new DefaultAutoRunnerJUnitListener());
		rn.addFirstListener(new SimpleRunListener());
		super.run(rn);
	}
	
	
	
}
