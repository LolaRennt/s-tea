package org.sky.auto.runner.statement;

import org.sky.auto.runner.listener.ThreadLocalRegistry;

public class ThreadLocalResetInterceptor implements Interceptor {


	public void interceptorBefore() {
		ThreadLocalRegistry.resetThreadLocals();
		
	}

	public void interceptorAfter() {
	//	ThreadLocalRegistry.resetThreadLocals();
		
	}

}
