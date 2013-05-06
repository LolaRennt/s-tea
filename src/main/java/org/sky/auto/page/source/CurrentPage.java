package org.sky.auto.page.source;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.sky.auto.base.AutoBase;
import org.sky.auto.report.RunTimeMethod;
import org.sky.auto.window.Window;

public class CurrentPage {
	private static Logger logger = Logger.getLogger(CurrentPage.class);
	private Response response;
	private PageHtml content;
	private WebDriver currentpage;
	private String url;
	private String title;
	private List<String> jsSrc;
	private List<String> cssHref;
	
	public CurrentPage(){
		currentpage = AutoBase.driver();
		this.url=currentpage.getCurrentUrl();
		content=new PageHtml(this.url);
		//response =new Response(this.url);
		this.title=currentpage.getTitle();
		jsSrc=content.getJavaScriptURL();
		cssHref=content.getCssLink();
	}
	/**返回当前页的url*/
	public String getCurrentUrl(){
		return url;
	}
	/**返回当前页的title*/
	public String getTitle(){
		return title;
	}
	
	/**
	 * 返回头信息的Map形式
	 * */
	public Map<String,String> getHeaders(){
		response = new Response(getCurrentUrl());
		HttpHeader header =new HttpHeader(response);
		Map<String,String> headmap=header.getHeadersMap();
		//header.closeResponse();
		response.closeResponse();
		return headmap;
	}
	
	/**返回当前页指定头信息的名称*/
	public String getHeaderValue(String name){
		return getHeaders().get(name);
	}
	/**返回本页面所有的Js地址的url*/
	public List<String> getJavaScriptURL(){
		return jsSrc;
	}
	
	/**返回本页面所有的Css的link地址*/
	public List<String> getCssLinks(){
		return cssHref;
	}

	/**返回当前页面的是否被压缩了*/
	public boolean isGzip(){
		Response response = new Response(this.url);
		boolean isgzip= response.isGzip();
		response.closeResponse();
		return isgzip;
	}
//	/**返回页面的响应时间*/
//	public long getLoadTime(){
//		response =new Response(this.url);
//		long time= response.getPageLoadTime();
//		response.closeResponse();
//		return time;
//	}
	
//	/**返回某个js的加载时间*/
//	public long getJavaScriptLoadTime(String name){
//		return content.getJavaScriptLoadTime(name);
//	}	
	
	/**返回头部的css的列表*/
	public List<String> getHeadCssLinks(){
		return content.getHeadCssLinks();
	}
	
	public int getStatusCode(){
		Response response = new Response(this.url);
		int code =response.getStatusCode();
		response.closeResponse();
		return code;
	}
	
	public String getPageSource(){
		return AutoBase.driver().getPageSource();
	}
	
	/**当前页面是否有指定的文本*/
	public void assertTextPresent(String text){
		if(getPageSource().contains(text)){
			logger.info("["+RunTimeMethod.getName()+"]"+"当前页面中存在此文本内容"+text+"校验成功！");
		}else{
			logger.error("["+RunTimeMethod.getName()+"]"+"当前页面中不存在此文本内容"+text+"校验失败！");
			Assert.fail();
		}
	}
	
	public void assertTextNotPresent(String text){
		if(!getPageSource().contains(text)){
			logger.info("["+RunTimeMethod.getName()+"]"+"当前页面中不存在此文本内容"+text+"校验成功！");
		}else{
			logger.error("["+RunTimeMethod.getName()+"]"+"当前页面中存在此文本内容"+text+"校验失败！");
			Assert.fail();
		}
	}
	
	
	/**是否产生alert*/
	public void assertAlert(){
		try{
			AutoBase.driver().switchTo().alert();
			logger.info("["+RunTimeMethod.getName()+"]"+"当前页面找到了alert,校验成功！");
			Window.selectDefaultWindow();
		}catch(Exception e){
			logger.error("["+RunTimeMethod.getName()+"]"+"没有找到alert,校验失败！");
			Assert.fail();
		}
	}
	
	public void assertTitle(String title){
		if(getTitle().equalsIgnoreCase(title)){
			logger.info("["+RunTimeMethod.getName()+"]"+"当前页面的title值校验成功！");
		}else{
			logger.error("["+RunTimeMethod.getName()+"]"+"当前页面的title值校验失败！");
		}
	}
	
	
}
