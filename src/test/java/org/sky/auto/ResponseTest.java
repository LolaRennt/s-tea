package org.sky.auto;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.log.Log;
import org.sky.auto.page.source.Response;

public class ResponseTest {

	
	
	
	
	@Test
	public void currentpageTest(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		//String url = AutoBase.currentpage().getCurrentUrl();
		Log.Debug("页面是否被GZIP压缩"+AutoBase.currentpage().isGzip());
		Log.Debug("状态码："+AutoBase.currentpage().getStatusCode());
		//Log.Debug("页面加载时间:"+AutoBase.currentpage().getLoadTime());
		AutoBase.closeAllWindow();
		//AutoBase.sleep(20);
		Response response =new Response();
		response.addHeader(HttpHeaders.HOST, "baidu.com");
		Log.Debug("页面加载时间:"+response.getPageLoadTime("http://www.baidu.com"));
		response.closeResponse();
	}
	
	
	@Test
	public void getLoadTimeTest(){
		Response response = new Response();
		System.out.println(response.getPageLoadTime("http://www.baidu.com"));
		System.out.println(response.getPageLoadTime("http://www.it168.com"));
		response.closeResponse();
	}
	
	
	@Test
	public void loadtimeTest(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		HttpClient client = new DefaultHttpClient();
		HttpGet hg = new HttpGet(driver.getCurrentUrl());
		long start = System.currentTimeMillis();
		try {
			client.execute(hg);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("--->"+(end-start));
		driver.close();
		
	}
	
	
	
	
}
