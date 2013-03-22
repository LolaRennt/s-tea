package org.sky.auto.page.source;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
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

public class Response {
	private List<Header> headerlist=new ArrayList<Header>();
	private String url;
	private HttpClient client;
	private HttpMethod httpmethod;
	public Response(HttpMethod method,String url){
		this.setClient(new DefaultHttpClient());
		this.httpmethod=method;
		this.url=url;
	}
	
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

	public void setHttpmethod(HttpMethod httpmethod) {
		this.httpmethod = httpmethod;
	}
	
	public Response(String url){
		this.client=new DefaultHttpClient();
		this.httpmethod=HttpMethod.GET;
		this.url=url;
	}
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
		HttpUriRequest urirequest=getHttpUriRequest(getHttpmethod());
		for(Header header: headerlist){
			urirequest.addHeader(header);
		}
		try {
			return this.client.execute(urirequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**此方法返回请求的响应信息
	 * @param url 请求的url
	 * @return 返回请求的响应信息
	 * */
	public HttpResponse response(String askurl){
		HttpUriRequest urirequest=getHttpUriRequest(getHttpmethod(),askurl);
		for(Header header: headerlist){
			urirequest.addHeader(header);
		}
		try {
			return this.client.execute(urirequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
			return this.client.execute(urirequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**得到页面首次加载的时间，首屏时间
	 * @param url请求的url地址
	 * @return 返回页面首屏加载的时间
	 * */
	public long getPageLoadTime(String askurl){
		long start  = System.currentTimeMillis();
		response(askurl);
		long end = System.currentTimeMillis();
		return end-start;
	}
	/**得到页面首次加载的时间，首屏时间
	 * @param hm请求的方法，比如get,post,put等
	 * @param url 请求的地址
	 * @return 返回页面首屏的加载时间
	 * */
	public long getPageLoadTime(HttpMethod hm,String askurl){
		HttpUriRequest requestMethod = getHttpUriRequest(hm, askurl);
		long start = System.currentTimeMillis();
		try {
			this.client.execute(requestMethod);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
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
	
	/**关闭请求*/
	public void closeResponse(){
		this.client.getConnectionManager().shutdown();
	}
	
}
