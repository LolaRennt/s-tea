package org.sky.auto.runner;

import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

public class JUnitRunner {
	private List<RunListener> listenerList;
	private Runner runner;
	
	public JUnitRunner(Runner runner){
		this.runner=runner;
	}
	
	public JUnitRunner(Runner runner,List<RunListener>listenerList){
		this.runner=runner;
		this.listenerList=listenerList;
	}
	/**没有提供过滤器和sort器，后期会添加功能！*/
	public void run(Class<?>clazz){
		RunNotifier notifier = new RunNotifier();
		Result result = new Result();
		notifier.addFirstListener(result.createListener());
		for(RunListener rl : listenerList){
			notifier.addListener(rl);
		}
		notifier.fireTestRunStarted(runner.getDescription());
		runner.run(notifier);
		notifier.fireTestRunFinished(result);
	}
	
	
	
	
	
}
