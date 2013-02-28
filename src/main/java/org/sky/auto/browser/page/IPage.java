package org.sky.auto.browser.page;

import org.openqa.selenium.WebElement;
import org.sky.auto.browser.element.*;
public interface IPage {
	/**获得定义好的元素*/
	public Contorl contorl(String id);
	
	public WebElement element(String id);
	
	/**得到页面的title*/
	public  String getTitle();
	/**得到所有的cookies*/
	public  String getAllCookies();
	/**得到指定名字的cookie*/
	public String getCookieByName(String name);

	/**删除该指定目录下的指定名字的cookie的所有的cookie*/
	public void deleteCookie(String name, String path);
	/**删除所有的cookie*/
	public void deleteAllCookie();
	
	
}
