package org.sky.auto.eventtest;


import org.junit.Test;

public class ExceptionTest {
	@Test
	public void testExcpetion() throws Exception{
	//	ProxyJUnitRunListener.register(new MyFailureListener());
		throw new Exception();
	}
}
