package org.sky.auto.page;


import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.sky.auto.base.AutoBase;
import org.sky.auto.element.Button;
import org.sky.auto.element.CheckBox;
import org.sky.auto.element.ComoboBox;
import org.sky.auto.element.Image;
import org.sky.auto.element.Link;
import org.sky.auto.element.ListElement;
import org.sky.auto.element.RadioButton;
import org.sky.auto.element.RichTextField;
import org.sky.auto.element.SElement;
import org.sky.auto.element.Table;
import org.sky.auto.element.TextField;
import org.sky.auto.page.source.CurrentPage;


public class Page extends CurrentPage{
	//protected static Page page = new Page();
	static Logger logger = Logger.getLogger(Page.class);
	static Object o;
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
	/**获得定义好的元素*/
	public SElement sElement(String id){
		return AutoBase.sElement(id);
	}
	
	public WebElement element(String id){
		return AutoBase.element(id);
	}
	
	/**得到页面的title*/
	public  String getTitle(){
		return AutoBase.driver().getTitle();
	}
	/**得到所有的cookies*/
	public  String getAllCookies() {
		Set<Cookie> cookies = AutoBase.driver().manage().getCookies();
		Iterator<Cookie> cookie = cookies.iterator();
		String cookieStr = "";
		while (cookie.hasNext()) {
			cookieStr = cookieStr + "|" + cookie.next().getValue();
		}
		return cookieStr;
	
	}
	/**得到指定名字的cookie*/
	public String getCookieByName(String name) {
		return AutoBase.driver().manage().getCookieNamed(name).getValue();
		}

	/**删除该指定目录下的指定名字的cookie的所有的cookie*/
	public void deleteCookie(String name, String path) {
		AutoBase.driver().manage().deleteCookieNamed(name);

	}
	/**删除所有的cookie*/
	public void deleteAllCookie() {
		AutoBase.driver().manage().deleteAllCookies();
	}
	
	public  ListElement listElement(String id){
		ListElement le= new ListElement(AutoBase.elements("id"));
		logger.info("元素LIST["+id+"]的操作--->");
		return le;
 	}
	public  Button button(String id){
		Button bt=new Button(element(id));
		logger.info("按钮["+id+"]的操作--->");
		return bt;
	}
	
	public  CheckBox checkBox(String id){
		CheckBox cb =new CheckBox(element(id));
		logger.info("CheckBox["+id+"]的操作--->");
		return cb;
	}
	
	public  ComoboBox comoboBox(String id){
		ComoboBox cb=new ComoboBox(element(id));
		logger.info("ComoboBox["+id+"]的操作--->");
		return cb;
	}
	
	public  Image image(String id){
		Image i = new Image(element(id));
		logger.info("Image["+id+"]的操作--->");
		return i;
	}
	
	public  Link link(String id){
		Link l =new Link(element(id));
		logger.info("Link["+id+"]的操作--->");
		return l;
	}
	
	public  RadioButton radioButton(String id){
		RadioButton rd = new RadioButton(element(id));
		logger.info("RadioButton["+id+"]的操作--->");
		return rd;
	}
	public  RichTextField richTextField(String id){
		RichTextField rtf = new RichTextField(element(id));
		logger.info("RichTextField["+id+"]的操作--->");
		return rtf;
	}
	public  Table table(String id){
		Table t=new Table(element(id));
		logger.info("Table["+id+"]的操作--->");
		return t;
	}
	public  TextField textField(String id){
		TextField tf = new TextField(element(id));
		logger.info("TextField["+id+"]的操作--->");
		return tf;
	}
	
}
