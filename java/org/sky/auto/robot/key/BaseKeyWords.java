package org.sky.auto.robot.key;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.sky.auto.base.AutoBase;

@RobotKeywords
public class BaseKeyWords {
	
	@RobotKeyword
	public void LogStart(){
		AutoBase.setLogStarted();
	}
	
//	
//	@RobotKeyword
//	public void initBrowser(){
//		
//	}
	
	
	@RobotKeyword
	public void destoryWindows(){
		if(!AutoBase.isClose_Status()){
			AutoBase.closeAllWindow();
		}
	}
	
	@RobotKeyword
	public void waitForLoad(Integer s){
		AutoBase.setElementWaitTime(s);
	}
}
