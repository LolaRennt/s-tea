package org.sky.auto.document.listener;

import org.sky.auto.driver.event.AutoRunnerListener;


public class MyListener extends AutoRunnerListener{
	/**这个类定义了我在执行click操作之前和之后，就打印出一行"---------------------"
	 * 直接覆盖父类的这些方法就行。加上你自己的逻辑就可以了，如果在一些操作过程中需要加入一些简单的逻辑之后我们就
	 * 可以通过这种方式来定制我们自己的服务了。
	 * 加载监听器的功能在@Test中有显示。
	 * */
	@Override
	public void beforeClickOn() {
		System.out.println("-------------------------------------------------------------");
	}
	
	@Override
	public void afterClickOn() {
		System.out.println("**************************************************************");
	}
}
