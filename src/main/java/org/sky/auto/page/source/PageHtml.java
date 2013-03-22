package org.sky.auto.page.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageHtml extends HttpHtml{
	
	private String url;
	private Response response;
	private HttpMethod method;
	public PageHtml(String url){
		this.url=url;
		this.method=HttpMethod.GET;
	}
	public PageHtml(String url,HttpMethod method){
		this.url=url;
		this.setHttpMethod(method);
	}
	
	@Override
	public String getContext() {
		HttpEntity entity=response.response().getEntity();
		try {
			return EntityUtils.toString(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getStatusCode() {
		return response.response().getStatusLine().getStatusCode();
	}

	@Override
	public  List<String> getCssLink() {
		List<String> csslist = new ArrayList<String>();
		Document dc = Jsoup.parse(getContext());
		Elements es = dc.select("link");
		Iterator<Element> iter = es.iterator();
		while(iter.hasNext()){
			Element e = iter.next();
			csslist.add(e.attr("href"));
		}
		return csslist;
	}

	@Override
	public List<String> getJavaScriptURL() {
		List<String> jslist = new ArrayList<String>();
		Document dc = Jsoup.parse(getContext());
		Elements es = dc.select("script");
		Iterator<Element> iter = es.iterator();
		while(iter.hasNext()){
			Element e = iter.next();
			jslist.add(e.attr("src"));
		}
		return jslist;
	}
	/**判断页面中是否有*/
	@Override
	boolean isGzip() {
		response.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
		HttpHeader header = new HttpHeader(response);
		String gzip=header.getHeaderValue(HttpHeaders.CONTENT_ENCODING);
		if(gzip.toLowerCase().contains(gzip)){
			return true;
		}
		return false;
	}


	public String getUrl() {
		return url;
	}
	/**设置需要访问的url*/
	public void setUrl(String url) {
		this.url = url;
	}
	/**得到指定js的加载时间*/
	public long getJavaScriptLoadTime(String name){
		for(String js:getJavaScriptURL()){
			if(js.endsWith(js)){
				Response resp = new Response();
				return resp.getPageLoadTime(js);
			}
		}
		return 0;
	}
	/**返回head头信息中的css的所有引用*/
	public List<String> getHeadCssLinks(){
		List<String> headercss = new ArrayList<String>();
		String headerContent = getContext().substring(getContext().indexOf("<head>"), getContext().indexOf("</head>"));
		Document dc = Jsoup.parse(headerContent);
		Elements es = dc.select("link");
		Iterator<Element> iter = es.iterator();
		while(iter.hasNext()){
			Element e = iter.next();
			headercss.add(e.attr("src"));
		}
		return headercss;
	}
	
	/**关闭实时的请求*/
	public void closeResponse(){
		this.response.closeResponse();
	}
	/**开启实时请求*/
	public void startResponse(){
		this.response=new Response(getHttpMethod(),getUrl());
		this.method=this.response.getHttpmethod();
	}
	/**@return 请求的方法*/
	public HttpMethod getHttpMethod() {
		return method;
	}
	/**设置请求方法*/
	public void setHttpMethod(HttpMethod method) {
		this.method = method;
	}
	/**页面加载时间*/
	public long getPageLoadTime(){
		return response.getPageLoadTime(getUrl());
	}
	
	
	
}
