package org.sky.auto.page.source;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class Response {
	private List<Header> headerlist=new ArrayList<Header>();
	private String url;
	private HttpClient client;
	private HttpMethod httpmethod;
	private HttpResponse resp;
	private HttpUriRequest httprequest;
	
	/**
	 * @param method 我们请求的http方法
	 * @param url 我们需要请求响应的URL地址
	 * */
	public Response(HttpMethod method,String url){
		this.setClient(new DefaultHttpClient());
		this.httpmethod=method;
		this.url=url;
	}
	/**
	 * @param 设置的请求客户端
	 * @param method 我们请求的http方法
	 * @param url 我们需要请求响应的URL地址
	 * */
	public Response(HttpClient client, HttpMethod method,String url){
		this.client=client;
		this.httpmethod=method;
		this.url=url;
	}
	/**
	 * @return 返回请求的方法
	 * */
	public HttpMethod getHttpmethod() {
		return httpmethod;
	}
	/**设置的请求的方法*/
	public void setHttpmethod(HttpMethod httpmethod) {
		this.httpmethod = httpmethod;
	}
	/**此构造方法，默认了请求方法为Get
	 * @param 请求的URL
	 * */
	public Response(String url){
		this.client=new DefaultHttpClient();
		this.httpmethod=HttpMethod.GET;
		this.url=url;
	}
	/**此构造方法默认了我们请求的方法为Get，并没有初始化URL*/
	public Response(){
		this.client=new DefaultHttpClient();
		this.httpmethod=HttpMethod.GET;
	}
	/**
	 * @return 返回设置的本地客户端HttpClient对象
	 * */
	public HttpClient getClient() {
		return client;
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}
	
	/**此方法返回请求的响应信息
	 * @return 返回请求的响应信息
	 * */
	public HttpResponse response(){
			httprequest=getHttpUriRequest(getHttpmethod());
			for(Header header: headerlist){
				httprequest.addHeader(header);
			}
			try {
				resp=this.client.execute(httprequest);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return resp;
	}
	/**此方法返回请求的响应信息
	 * @param url 请求的url
	 * @return 返回请求的响应信息
	 * */
	public HttpResponse response(String askurl){
			httprequest=getHttpUriRequest(getHttpmethod(),askurl);
			for(Header header: headerlist){
				httprequest.addHeader(header);
			}
			try {
				resp= this.client.execute(httprequest);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return resp;
	}
	
	private HttpUriRequest getHttpUriRequest(HttpMethod method,String askurl){
		switch(method){
		case GET:
			return new HttpGet(askurl);
		case POST:
			return new HttpPost(askurl);
		case DELETE:
			return new HttpDelete(askurl);
		case PUT:
			return new HttpPut(askurl);
		case PATCH:
			return new HttpPatch(askurl);
		case HEAD:
			return new HttpHead(askurl);
		default:
			break;
		}
		return null;
	}
	
	private HttpUriRequest getHttpUriRequest(HttpMethod method){
		switch(method){
		case GET:
			return new HttpGet(getUrl());
		case POST:
			return new HttpPost(getUrl());
		case DELETE:
			return new HttpDelete(getUrl());
		case PUT:
			return new HttpPut(getUrl());
		case PATCH:
			return new HttpPatch(url);
		case HEAD:
			return new HttpHead(url);
		default:
			break;
		}
		return null;
	}
	
	/**此方法返回请求的响应信息
	 * @param 请求的方法
	 * @param 请求的url
	 * @return 返回请求的响应信息
	 * */
	public HttpResponse response(HttpMethod method,String askurl) {
			setHttpmethod(method);
			HttpUriRequest urirequest=getHttpUriRequest(method, askurl);
			for(Header header: headerlist){
				urirequest.addHeader(header);
			}
			try {
				resp= this.client.execute(urirequest);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return resp;
	}
	/**得到页面首次加载的时间，首屏时间
	 * @param url请求的url地址
	 * @return 返回页面首屏加载的时间
	 * */
	public long getPageLoadTime(String askurl){
		long start  = System.currentTimeMillis();
		response(askurl);
		long end = System.currentTimeMillis();
		abort();
		return end-start;
	}
	/**得到页面首次加载的时间，首屏时间
	 * @param hm请求的方法，比如get,post,put等
	 * @param url 请求的地址
	 * @return 返回页面首屏的加载时间
	 * */
	public long getPageLoadTime(HttpMethod hm,String askurl){
		httprequest = getHttpUriRequest(hm, askurl);
		long start = System.currentTimeMillis();
		try {
			this.client.execute(httprequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		httprequest.abort();
		return end-start;
	}
	/**得到页面首次加载的时间，首屏时间
	 * @param hm请求的方法，比如get,post,put等
	 * @return 返回页面首屏的加载时间
	 * */
	public long getPageLoadTime(HttpMethod hm){
		HttpUriRequest requestMethod = getHttpUriRequest(hm, getUrl());
		long start = System.currentTimeMillis();
		try {
			this.client.execute(requestMethod);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		requestMethod.abort();
		return end-start;
	}
	/**得到页面首次加载的时间，首屏时间*/
	public long getPageLoadTime(){
		HttpUriRequest requestMethod = getHttpUriRequest(getHttpmethod(), getUrl());
		long start = System.currentTimeMillis();
		try {
			this.client.execute(requestMethod);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		requestMethod.abort();
		return end-start;
	}
	
	/**添加头信息
	 * @param name头信息的名字
	 * @param value 头信息的值
	 * */
	public void addHeader(String name,String value){
		headerlist.add(new BasicHeader(name,value));
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/**关闭HTTP的请求，同时关闭客户端*/
	public void closeResponse(){
		//this.abort();
		this.client.getConnectionManager().shutdown();
	}
	public HttpUriRequest getHttprequest() {
		return httprequest;
	}
	public void setHttprequest(HttpUriRequest httprequest) {
		this.httprequest = httprequest;
	}
	
	/**停止http的请求，但是不关闭客户端*/
	public void abort(){
		getHttprequest().abort();
	}
	
	public String getContent(){
		try {
			String content= EntityUtils.toString(response().getEntity());
			abort();
			return content;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getStatusCode(){
		int code = response().getStatusLine().getStatusCode();
		abort();
		return code;
	}
	
	
	/**判断页面中是否有*/
	public boolean isGzip() {
		boolean iszip=false;
		this.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
		HttpHeader header = new HttpHeader(this);
		String gzip=header.getHeaderValue(HttpHeaders.CONTENT_ENCODING);
		if(gzip!=null&&gzip.toLowerCase().contains(gzip)){
			iszip=true;	
		}
		return iszip;
	}
	
	/**判断页面中是否有*/
	public boolean isGzip(String url) {
		this.setUrl(url);
		boolean iszip=false;
		this.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
		HttpHeader header = new HttpHeader(this);
		String gzip=header.getHeaderValue(HttpHeaders.CONTENT_ENCODING);
		if(gzip!=null&&gzip.toLowerCase().contains(gzip)){
			iszip=true;	
		}
		return iszip;
	}
	
	
}
