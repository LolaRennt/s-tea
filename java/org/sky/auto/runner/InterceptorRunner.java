package org.sky.auto.runner;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.sky.auto.anno.InterceptorClass;
import org.sky.auto.runner.statement.Interceptor;
import org.sky.auto.runner.statement.InterceptorStatement;

public class InterceptorRunner extends BlockJUnit4ClassRunner{

	public InterceptorRunner(Class<?> arg0) throws Throwable {
		super(arg0);
	}
	
	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		InterceptorStatement statement = new InterceptorStatement(method,test);
		InterceptorClass anno = test.getClass().getAnnotation(InterceptorClass.class);
		Class<?>[] clazzs = anno.value();
		try{
			for(Class<?> clazz : clazzs){
				statement.addInterceptor((Interceptor)clazz.newInstance());
			}
		}catch(IllegalAccessException ilex){
			ilex.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}
		return statement;
	}
	
}
