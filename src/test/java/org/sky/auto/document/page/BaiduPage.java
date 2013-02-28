package org.sky.auto.document.page;

import org.sky.auto.base.AutoBase;
import org.sky.auto.page.Page;

public class BaiduPage  extends Page{
	
	public void search(String text){
		AutoBase.sElement("百度首页-搜索框").sendKeys(text);
		AutoBase.sElement("百度首页-搜索按钮").click();
	}
}
