package org.sky.example;

import org.sky.auto.anno.Register;


/**这个例子说明了监听器的使用，监听器分为两种，一种是动作的，一种还是运行时的，运行时的监听器需要继承RunListener
 * 自己定义一个监听器的话只需要在定义的监听器上面加上@Register注解就可以了。
 * 另外ProxyRunnerListener.register(Class<RunnerListener> clazz)也可以添加监听器
 * ProxyRunnerListener.unregister(Class<RunnnerListener> clazz)可以卸载监听器
 * 
 * */
@Register
public class ListenerExample implements org.sky.auto.proxy.RunnerListener{

	@Override
	public void afterClickOn() {
		System.out.println("你进行了点击操作！");
	}

	@Override
	public void beforeClickOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterOpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSendkeys() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSendkeys() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforemoveToElement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aftermoveToElement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeselectWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterselectWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforerunJS() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterrunJS() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforecloseAllWindows() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aftercloseAllWindows() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforecloseCurrentWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aftercloseCurrentWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforescrollTo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterscrollTo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforerefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterrefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeforward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterforward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeback() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterback() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforetakeScreenShot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aftertakeScreenShot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforedealAlert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterdealAlert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforedealPrompt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterdealPrompt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforedealConfirm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterdealConfirm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforemouserOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aftermouserOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforekeyUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterkeyUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforekeyDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterkeyDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforescroll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterscroll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforedragAndDrop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterdragAndDrop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeleftUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterleftUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeleftDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterleftDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeclear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterclear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforedoubleClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterdoubleClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeaddLocator() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afteraddLocator() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforemaxWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aftermaxWindow() {
		// TODO Auto-generated method stub
		
	}

}
