package org.sky.auto.runner.statement;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class InterceptorStatement extends Statement{
	private final FrameworkMethod testMethod;
    private Object target;
	public InterceptorStatement(FrameworkMethod testMethod, Object target) {
		this.testMethod=testMethod;
		this.target=target;
	}
	private List<Interceptor> interceptors = new ArrayList<Interceptor>();
	
	

	@Override
	public void evaluate() throws Throwable {
		addInterceptor(new ThreadLocalResetInterceptor());
		for(Interceptor interceptor:interceptors){
			interceptor.interceptorBefore();
		}
		testMethod.invokeExplosively(target);
		for(Interceptor interceptor:interceptors){
			interceptor.interceptorAfter();
		}
	}
	public void addInterceptor(Interceptor interceptor){
		interceptors.add(interceptor);
	}
}
