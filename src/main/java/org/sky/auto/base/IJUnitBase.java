package org.sky.auto.base;

import org.junit.After;
import org.junit.Before;


public interface IJUnitBase {
	@Before
	public void setUp();
	@After
	public void destory();
}
