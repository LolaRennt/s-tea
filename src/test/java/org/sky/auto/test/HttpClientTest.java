package org.sky.auto.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientTest {
	public int getStatusCode(String url){
		HttpClient client = new DefaultHttpClient();
		HttpGet hg = new HttpGet(url);
		HttpResponse hr = null;
		try {
			hr = client.execute(hg);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int code = hr.getStatusLine().getStatusCode();
		return code;
	}
}
