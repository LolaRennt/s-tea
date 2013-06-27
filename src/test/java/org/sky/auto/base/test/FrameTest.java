package org.sky.auto.base.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FrameTest {
	
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://product.it168.com/list/b/0301_1.shtml");
		String pagesource = driver.getPageSource();
		//System.out.println(pagesource);
		Document doc = Jsoup.parse(pagesource);
		Element element = doc.select("iframe").first();
		Element element2 = element.parent();
		//String jurl=element.attr("src");
		//Element e=doc.select("div#adv_baidu").first();
		System.out.println(element2.tagName());
		System.out.println(element2.attr("id"));
		System.out.println(element2.childNodeSize());
		driver.close();
		
	}

}
