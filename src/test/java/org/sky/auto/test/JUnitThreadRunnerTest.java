package org.sky.auto.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.runner.MyJUnitThreadRunner;

@RunWith(MyJUnitThreadRunner.class)
@ThreadRunner(threads=1)
public class JUnitThreadRunnerTest {
	@Test
	public void methodOne(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		driver.quit();
	}
	@Test
	public void methodTwo(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.hao123.com");
		driver.quit();
	}
	@Test
	public void methodThree(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.sohu.com");
		driver.quit();
	}
	@Test
	public void methodFour(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.it168.com");
		driver.quit();
	}
	
}
