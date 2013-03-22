package org.sky.auto.page.source;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;


public class HttpHeader {
	//private HttpClient client;
	private Response response;
	
	public HttpHeader(Response response){
		this.setResponse(response);
	}
	
	/**得到响应的对象
	 * @return 返回此头信息的响应对象
	 * */
	public Response getResponse() {
		return response;
	}
	/**
	 * 设置头信息的响应对象
	 * */
	public void setResponse(Response response) {
		this.response = response;
	}
	/**得到所有的头信息的数组
	 * @return 返回所有的头信息的数组
	 * */
	public Header[] getHeaders(){
		return getResponse().response().getAllHeaders();
	}
 	
	/**得到头信息的Map
	 *@param url 
	 *@return 返回Header头信息的所有Map
	 * */
	public Map<String,String> getHeadersMap(){
		Map<String,String> headerMap = new HashMap<String,String>();
		for(Header header:getHeaders()){
			headerMap.put(header.getName(), header.getValue());
		}
		return headerMap;
	}
	
	/**得到指定头信息的返回值
	 * @param 指定头信息的名字
	 * @return 返回指定头信息的返回值
	 * */
	public String getHeaderValue(String name){
		return getHeadersMap().get(name);
	}
	
}
