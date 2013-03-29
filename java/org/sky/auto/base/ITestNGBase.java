package org.sky.auto.base;

import org.sky.auto.data.testng.excel.TestNGFeed;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**这个抽象类提供的是TestNg的初始化的类*/
public abstract class ITestNGBase extends TestNGFeed{
	@BeforeMethod
	public abstract void setUp();
	
	@AfterMethod
	public abstract void destory();
	
}
