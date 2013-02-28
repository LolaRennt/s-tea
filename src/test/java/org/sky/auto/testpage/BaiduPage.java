package org.sky.auto.testpage;

import org.sky.auto.page.Page;

public class BaiduPage extends Page{
	public void search(String text){
		sElement("百度首页-搜索框").sendKeys(text);
		sElement("百度首页-搜索按钮").click();
	}
}	
