package org.sky.auto.page.source;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.sky.auto.base.AutoBase;

public class CurrentPage {
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
		response =new Response(this.url);
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
		HttpHeader header =new HttpHeader(response);
		Map<String,String> headmap=header.getHeadersMap();
		header.closeResponse();
		return headmap;
	}
	
	/**返回当前页指定头信息的名称*/
	public String getHeaderValue(String name){
		HttpHeader header =new HttpHeader(response);
		String headvalue = header.getHeaderValue(name);
		header.closeResponse();
		return headvalue;
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
		return content.isGzip();
	}
	/**返回页面的响应时间*/
	public long getLoadTime(){
		return response.getPageLoadTime(getCurrentUrl());
	}
	
	/**返回某个js的加载时间*/
	public long getJavaScriptLoadTime(String name){
		return content.getJavaScriptLoadTime(name);
	}	
	
	/**返回头部的css的列表*/
	public List<String> getHeadCssLinks(){
		return content.getHeadCssLinks();
	}
}
