package org.sky.auto.verify;

import org.sky.auto.base.AutoBase;

public class Assert extends org.junit.Assert{
	
	/**判断当前页面的title是否正确*/
	public void assertTitle(String title){
		Assert.assertEquals(AutoBase.currentpage().getTitle(), title);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
