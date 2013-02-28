package org.sky.auto.base;

import org.sky.auto.data.testng.excel.TestNGFeed;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public abstract class ITestNGBase extends TestNGFeed{
	@BeforeMethod
	public abstract void setUp();
	
	@AfterMethod
	public abstract void destory();
	
}
