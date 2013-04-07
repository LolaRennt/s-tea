package org.sky.auto;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class URLtest {

	@Test
	public void url(){
		try {
			URL url = new URL("http://www.baidu.com");
			System.out.println(url.getProtocol());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
