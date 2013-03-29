package org.sky.auto.base;

import org.junit.After;
import org.junit.Before;

/**这个接口提供了一个简单的JUnit初始化的功能*/
public interface IJUnitBase {
	@Before
	public void setUp();
	@After
	public void destory();
}
