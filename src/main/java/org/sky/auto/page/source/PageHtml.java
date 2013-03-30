package org.sky.auto.page.source;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageHtml extends HttpHtml{
	
	private String url;
	public PageHtml(String url){
		this.url=url;
	}
	
	
	@Override
	public String getContext() {
		Response response = new Response(this.url);
		String content= response.getContent();
		response.abort();
		response.closeResponse();
		return content;
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
	public boolean isGzip() {
		boolean iszip=false;
		Response response = new Response(getUrl());
		response.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
		HttpHeader header = new HttpHeader(response);
		String gzip=header.getHeaderValue(HttpHeaders.CONTENT_ENCODING);
		if(gzip!=null&&gzip.toLowerCase().contains(gzip)){
			iszip=true;	
		}
		return iszip;
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
		Response response = new Response(getUrl());
		for(String js:getJavaScriptURL()){
			if(js.endsWith(js)){
				long time=response.getPageLoadTime();
				return time;
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
}
