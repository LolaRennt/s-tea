package org.sky.auto;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.runner.BaseJUnitAutoRunner;


@RunWith(BaseJUnitAutoRunner.class)
@ThreadRunner(threads=1)
public class TextTest {
	
	@Test 
	public void hello(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.closeAllWindow();
	}
}
