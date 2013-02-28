package org.sky.auto.browser.page;

import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;
import org.sky.auto.browser.element.Contorl;



public class Page implements IPage{
	private static Object o;
	@SuppressWarnings("unchecked")
	public  final <T> T load(Class<T> clazz){
		Page.o=null;
		if(o==null){
			try {
				o=Class.forName(clazz.getName()).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (T) o;
		}
		return (T) o;
	}
	
	private IBrowser browser;
	public Page(IBrowser browser){
		this.browser=browser;
	}
	
	public Contorl contorl(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public WebElement element(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getAllCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getCookieByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void deleteCookie(String name, String path) {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteAllCookie() {
		// TODO Auto-generated method stub
		
	}
	public IBrowser getBrowser() {
		return browser;
	}
	public void setBrowser(IBrowser browser) {
		this.browser = browser;
	}

}
