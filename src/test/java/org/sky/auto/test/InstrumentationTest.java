package org.sky.auto.test;


import org.junit.Test;
import org.sky.auto.intrumentation.*;
public class InstrumentationTest {
	
	@Test
	public void testClassPool(){
		System.out.println("--------------------------------------------------------");
		for(Class<?> c:ClassPool.getClassPool()){
			System.out.println(c.getName());
		}
		//System.out.println(ClassFileLocator.getClassNames());
	}
}
