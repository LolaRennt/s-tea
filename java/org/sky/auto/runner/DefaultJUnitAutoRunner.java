package org.sky.auto.runner;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.sky.auto.runner.listener.DefaultAutoRunnerJUnitListener;

public class DefaultJUnitAutoRunner extends BaseJUnitAutoRunner{

	public DefaultJUnitAutoRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}
	
	@Override
	public void run(RunNotifier rn) {
		rn.addListener(new DefaultAutoRunnerJUnitListener());
		super.run(rn);
	}
	
	
	
}
