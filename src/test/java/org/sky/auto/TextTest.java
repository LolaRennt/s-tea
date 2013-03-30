package org.sky.auto;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.page.source.Response;
import org.sky.auto.runner.BaseJUnitAutoRunner;


@RunWith(BaseJUnitAutoRunner.class)
@ThreadRunner(threads=1)
public class TextTest {
	
	@Test 
	public void hello(){
		Response response = new Response("http://www.baidu.com");
		System.out.println(response.getPageLoadTime());
		response.closeResponse();
	}
}
