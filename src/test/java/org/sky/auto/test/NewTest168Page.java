package org.sky.auto.test;

import org.sky.auto.base.AutoBase;
import org.sky.auto.base.TestNGRemoteBase;
import org.testng.annotations.Test;

public class NewTest168Page extends TestNGRemoteBase{
	@Test
	public void test168(){
		AutoBase.open("http://www.it168.com");
		AutoBase.closeAllWindow();
	}
}
