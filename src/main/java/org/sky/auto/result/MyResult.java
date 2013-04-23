package org.sky.auto.result;

import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.sky.auto.mail.date.SimpleDate;

public class MyResult {
	private Result result;
	public void setFailure(List<Failure> failure) {
		this.failure = failure;
	}

	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}

	public void setRunCount(int runCount) {
		this.runCount = runCount;
	}

	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private List<Failure> failure;
	private int failureCount;
	private int runCount;
	private long runTime;
	private String time;
	public MyResult(Result result){
		this.setResult(result);
		this.failure=result.getFailures();
		this.runCount=result.getRunCount();
		this.runTime=result.getRunTime();
		this.time=SimpleDate.getSimpleDateFormat();
	}
	
	public List<Failure> getFailure(){
		return this.failure;
	}
	
	public int getFailureCount(){
		return this.failureCount;
	}
	
	public int getRunCount(){
		return this.runCount;
	}
	
	public long getRunTime(){
		return this.runTime;
	}
	
	public String getTime(){
		return this.time;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	
	
}
