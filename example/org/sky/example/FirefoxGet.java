package org.sky.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.log.Log;
import org.sky.auto.window.Window;

import com.github.lmm.core.Auto;

public class FirefoxGet {

	@Test
	public void get(){
		/**我们以Firefox为例演示打开链接的操作
		 * 一般的打开操作使用get()就可以了
		 * */
		FirefoxDriver firefox= new FirefoxDriver();
		firefox.get("http://www.baidu.com");
		firefox.close();
	}
	
	@Test
	public void get2(){
		FirefoxDriver firefox = new FirefoxDriver();
		firefox.navigate().to("http://www.baidu.com");
		firefox.close();
	}
	
	@Test
	public void title(){
		FirefoxDriver firefox= new FirefoxDriver();
		//访问百度首页
		firefox.get("http://www.baidu.com");
		String title = firefox.getTitle();
		String url = firefox.getCurrentUrl();
		//在控制台输出title和url的值
		System.out.println("当前页面的title值为"+title);
		System.out.println("当前页面的url值"+url);
		//关闭浏览器
		firefox.close();
	}
	
	@Test
	public void steatitle(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		String title=AutoBase.currentpage().getTitle();
		String url = AutoBase.currentpage().getCurrentUrl();
		Log.INFO(title);
		Log.INFO(url);
		AutoBase.closeAllWindow();
	}
	
	@Test
	public void stea3title(){
		Auto.require("firefox");
		Auto.open("http://www.baidu.com");
		String title=Auto.currentage().getTitle();
		String url = Auto.currentage().getUrl();
		System.out.println(title);
		System.out.println(url);
	}
	
	@Test
	public void navigate(){
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		driver.findElement(By.id("kw")).sendKeys("北京");
		driver.findElement(By.id("su")).click();
		//我们在百度首页搜索了北京关键字，然后点击搜索，然后页面会跳转到结果页面。
		//我们让浏览器后退到百度首页
		driver.navigate().back();
		//我们再让浏览器返回到结果页面
		driver.navigate().forward();
		//我们刷新一下页面
		driver.navigate().refresh();
		//退出浏览器
		driver.quit();
	}
	
	@Test
	public void steanavigate(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.sElement(By.id("kw")).sendKeys("北京");
		AutoBase.sElement(By.id("su")).click();
		Window.back();
		Window.forward();
		Window.refresh();
		AutoBase.closeAllWindow();
	}
	
	@Test
	public void stea3navigate(){
		Auto.require("firefox");
		Auto.open("http://www.baidu.com");
		Auto.currentage().element(By.id("kw")).input("北京");
		Auto.currentage().element(By.id("su")).click();
		Auto.browser().back();
		//Auto.back();这两种方式都行。Auto的含义就是一个代理浏览器
		Auto.browser().forward();
		Auto.browser().refresh();
		Auto.closeAllWindows();
	}
	
	@Test
	public void close(){
		FirefoxDriver driver =new FirefoxDriver();
		driver.get("http://www.baidu.com");
		driver.close();
	}
	@Test
	public void steaClose(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.closeAllWindow();
	}
	@Test
	public void steacurrentClose(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.openNew().open("http://ibiubiu.herokuapp.com");
		AutoBase.closeCurrentWindow();
	}
	
	
	@Test
	public void stea3Close(){
		Auto.require("firefox");
		Auto.open("http://www.baidu.com");
		Auto.closeAllWindows();
	}
	
	@Test
	public void stea3currentClose(){
		Auto.require("firefox");
		Auto.open("http://www.baidu.com");
		//在新的窗口打开一个新的页面
		Auto.currentage().openNewWindow("http://ibiubiu.herokuapp.com");
		Auto.currentage().closepage();
	}
	
	
	
	
	
	
}
