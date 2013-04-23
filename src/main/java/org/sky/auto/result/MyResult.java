package org.sky.auto.result;

import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.sky.auto.mail.date.SimpleDate;

public class MyResult {
	private Result result;
	public MyResult(Result result){
		this.result=result;
	}
	
	public List<Failure> getFailure(){
		return result.getFailures();
	}
	
	public int getFailureCount(){
		return result.getFailureCount();
	}
	
	public int getRunCount(){
		return result.getRunCount();
	}
	
	public long getRunTime(){
		return result.getRunTime();
	}
	
	public String getTime(){
		return SimpleDate.getSimpleDateFormat();
	}
	
	
	
}
