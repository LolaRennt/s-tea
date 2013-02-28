package org.sky.auto.robot.run;

import org.junit.Test;
import org.robotframework.RobotFramework;
import org.sky.auto.exception.MyAutoException;

public class AutoKeyWordRunner {
	@Test
	public void run(){
		
		
		int rc = RobotFramework.run(new String[]{"--outputdir","/Users/ouamaqing/robotHtml","txt"});
		if(rc==0){
			System.out.println("All tests passed");
		}else if(rc<=250){
			System.out.println(rc+"tests failed");
			throw new MyAutoException("在测试过程中出现了错误！请查看robot日志");
		}else{
			System.out.println("Error occurred");
			throw new MyAutoException("在测试过程中出现了错误！请查看robot日志");
		}
	}
	
	
	
}
