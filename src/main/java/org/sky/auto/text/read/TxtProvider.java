package org.sky.auto.text.read;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.sky.auto.element.SElement;
import org.sky.auto.exception.NotFoundLoadSourceException;

/**
 * 在内存里面会加载一部分id的内容，用来扫描资源文件是否存在重名的情况，如果存在的话会报错。
 * 这个类则提供了WebElement元素的转化方法
 * */
public class TxtProvider {
	
	
	/**
	 * @return 返回WebElement类型的元素，只有在查找的时候才会被调用
	 * */
	public WebElement element(String id){
		Map<String, ChainLink> lmap=TxtLoader.load();
		if(lmap.size()==0){
			throw new NotFoundLoadSourceException("加载txt资源的时候出现了错误！请查看资源配置和程序设定是否正确！");
		}
		ChainLink cl=lmap.get(id);
		return cl.element();
	}
	/**返回SElement类型的元素*/
	public SElement sElement(String id){
		return new SElement(element(id));
	}
	
	
	
	
	
}
