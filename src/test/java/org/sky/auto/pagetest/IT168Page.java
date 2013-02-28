package org.sky.auto.pagetest;

import org.sky.auto.page.Page;
import org.sky.auto.window.Window;

public class IT168Page extends Page{
//	public IT168Page(){
//		super();
//	}
	public void enterPB(){
		Window.scrollTo(sElement("笔记本浮层"));
		sElement("笔记本浮层").mouseOver();
		sElement("笔记本链接").click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
