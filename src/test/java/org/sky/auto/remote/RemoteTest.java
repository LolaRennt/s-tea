package org.sky.auto.remote;

import org.sky.auto.base.AutoBase;
import org.sky.auto.base.TestNGRemoteBase;
import org.testng.annotations.Test;

public class RemoteTest extends TestNGRemoteBase{
	@Test
	public void method1(){
		AutoBase.open("http://www.baidu.com");
	}
	
	@Test
	public void method2(){
		AutoBase.open("http://www.sohu.com");
	}
	@Test
	public void method3(){
		AutoBase.open("http://www.hao123.com");
	}
}
