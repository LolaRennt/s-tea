package org.sky.example;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;

import com.github.lmm.core.Auto;

public class LocatorTest {
	
	//@Test
	public void clickButton(){
		//首先我们启动浏览器
		WebDriver driver = new FirefoxDriver();
		//最大化浏览器，方便我们观察
		driver.manage().window().maximize();
		//访问存在例子的页面
		driver.get("http://localhost:3000/action");
		//通过页面的内容和简单的熟悉操作，我们知道我们要找的按钮在"通过ID来定位元素的"隐藏栏目下，所以我们要写的操作就是先去点击这个栏
		//目，然后再去查找里面的元素,在这个例子中我们处理的一个比较复杂的html元素。我们先去找它的id值。
		driver.findElement(By.id("action-id")).click();
		driver.findElement(By.id("action-id-button")).click();
		driver.findElement(By.id("id-model-close")).click();
		driver.close();
	}
	
	//@Test
	public void steaclickButton(){
		//访问存在例子的页面
		AutoBase.open(Browser.Firefox, "http://ibiubiu.herokuapp.action");
		//进入ID定位元素的隐藏栏目中
		AutoBase.sElement(By.id("action-id")).click();
		//点击蓝色按钮，等待弹出模态窗口
		AutoBase.sElement(By.id("action-id-button")).click();
		//关闭模态窗口
		AutoBase.sElement(By.id("id-model-close")).click();
		//关闭浏览器
		AutoBase.closeAllWindow();
	}
	
	//@Test
	public void stea3clickButton(){
		Auto.require("firefox");
		Auto.open("http://ibiubiu.herokuapp.com");
		//进入ID定位元素的隐藏栏目中
		Auto.currentpage().element(By.id("action-id")).click();
		//点击蓝色按钮，等待弹出模态窗口
		Auto.currentpage().element(By.id("action-id-button")).click();
		//关闭模态窗口
		Auto.currentpage().element(By.id("id-model-close")).click();
		//关闭浏览器
		Auto.closeAllWindows();
	}
	
	//@Test
	public void nameTest(){
		WebDriver driver = new FirefoxDriver();
		//driver.get("http://ibiubiu.herokuapp.com");
		driver.get("http://localhost:3000/action");
		driver.findElement(By.id("action-name")).click();
		driver.findElement(By.name("action-name-buttonright")).click();
		driver.findElement(By.name("name-alert")).click();
		driver.switchTo().alert().accept();
		driver.quit();
	}
	
	//@Test
	public void steanameTest(){
		AutoBase.open(Browser.Firefox, "http://ibiubiu.herokuapp.com");
		//进入Name定位元素的隐藏栏目中
		AutoBase.sElement(By.id("action-name")).click();
		//点击下拉按钮
		AutoBase.sElement(By.name("action-name-buttonright")).click();
		//点击弹出的连接选项，并且弹出alert
		AutoBase.sElement(By.name("name-alert")).click();
		//关闭弹出的alert窗口
		Window.dealAlert();
		//关闭浏览器
		AutoBase.closeAllWindow();
	}
	
	//@Test
	public void stea3nameTest(){
		Auto.require("firefox");
		Auto.open("http://ibiubiu.herokuapp.com");
		//进入Name定位元素的隐藏栏目中
		Auto.currentpage().element(By.id("action-name")).click();
		//点击下拉按钮
		Auto.currentpage().element(By.name("action-name-buttonright")).click();
		//点击弹出的连接选项，并且弹出alert
		Auto.currentpage().element(By.name("name-alert")).click();
		//关闭浏览器
		Auto.closeAllWindows();
	}
	

	//@Test
	public void clickxpath() throws InterruptedException{
		//首先我们启动浏览器
		WebDriver driver = new FirefoxDriver();
		//最大化浏览器，方便我们观察
		driver.manage().window().maximize();
		//给元素设置一个查找的时间，防止我们在查找元素的时候出现找不到的情况。
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//访问存在例子的页面
		driver.get("http://localhost:3000/action");
		//通过页面的内容和简单的熟悉操作，我们知道我们要找的按钮在"通过ID来定位元素的"隐藏栏目下，所以我们要写的操作就是先去点击这个栏
		//目，然后再去查找里面的元素,在这个例子中我们处理的一个比较复杂的html元素。我们先去找它的id值。
		driver.findElement(By.xpath(".//*[@id='action-xpath']")).click();
		driver.findElement(By.xpath(".//*[@id='locatorXpath']/div/div[2]/a")).click();
		driver.findElement(By.xpath(".//*[@id='xpath-model-close']")).click();
		driver.close();
	}
	
	//@Test
	public void steaclickxpath(){
		//首先我们启动浏览器
		AutoBase.open(Browser.Firefox, "http://ibiubiu.herokuapp.com");
		//给元素设置一个查找的时间，防止我们在查找元素的时候出现找不到的情况。
		AutoBase.setElementWaitTime(10);
		//通过页面的内容和简单的熟悉操作，我们知道我们要找的按钮在"通过ID来定位元素的"隐藏栏目下，所以我们要写的操作就是先去点击这个栏
		//目，然后再去查找里面的元素,在这个例子中我们处理的一个比较复杂的html元素。我们先去找它的id值。
		AutoBase.sElement(By.xpath(".//*[@id='action-xpath']")).click();
		AutoBase.sElement(By.xpath(".//*[@id='locatorXpath']/div/div[2]/a")).click();
		AutoBase.sElement(By.xpath(".//*[@id='xpath-model-close']")).click();
		AutoBase.closeAllWindow();
	}
	//@Test
	public void stea3clickxpath(){
		//首先我们设定浏览器
		Auto.require("firefox");
		//通过页面的内容和简单的熟悉操作，我们知道我们要找的按钮在"通过ID来定位元素的"隐藏栏目下，所以我们要写的操作就是先去点击这个栏
		//目，然后再去查找里面的元素,在这个例子中我们处理的一个比较复杂的html元素。我们先去找它的id值。
		Auto.currentpage().element(By.xpath(".//*[@id='action-xpath']")).click();
		Auto.currentpage().element(By.xpath(".//*[@id='locatorXpath']/div/div[2]/a")).click();
		Auto.currentpage().element(By.xpath(".//*[@id='xpath-model-close']")).click();
		Auto.closeAllWindows();
	}
	
	//@Test
	public void tagtest() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:3000/action");
		//点击开tagname元素定位的隐藏栏目
		driver.findElement(By.id("action-tagname")).click();
		Thread.sleep(3000);
		//找到我们想要找的元素，通过一层层的定位元素的位置来实现，我们通过观察页面的源码，然后再来对比我一层层的查找元素的方式就是层级定位。
		WebElement textElement=driver.findElement(By.xpath(".//*[@id='locatorTagName']/div/div[1]"));
		String text = textElement.getText();
		//我们把获取到的内容通过alert的形式打印出来，然后等待3秒钟之后再关掉alert和浏览器。
		((JavascriptExecutor)driver).executeScript("alert('"+text+"')");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		driver.quit();
	}
	
	//@Test
	public void steatag(){
		AutoBase.open(Browser.Firefox, "http://localhost:3000/action");
		AutoBase.sElement(By.id("action-tagname")).click();
		String text=AutoBase.sElement(By.id("locatorTagName")).childElement(By.className("accordion-inner"))
				.childElement(By.tagName("div"),0).getText();
		Window.runJS("alert('"+text+"')");
		AutoBase.sleep(3);
		Window.dealAlert();
		AutoBase.closeAllWindow();
	}
	
	//@Test
	public void stea3tag(){
		Auto.require("firefox");
		Auto.open("http://localhost:3000/action");
		Auto.currentpage().element(By.id("action-tagname")).click();
		//这是s-tea3.0里面的定位方式，jsoup的定位方式。
		String text=Auto.currentpage().node("#locatorTagName").node("[class^=well]").getText();
		Auto.currentpage().runJavaScript("alert('"+text+"')");
		AutoBase.sleep(3);
		Auto.currentpage().dealAlert();
		Auto.closeAllWindows();
	}
	
	//@Test
	public void css() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:3000/action");
		//点击开tagname元素定位的隐藏栏目
		driver.findElement(By.id("action-css")).click();
		driver.findElement(By.cssSelector(".alert button")).click();
		Thread.sleep(3000);
		driver.quit();
	}
		//@Test
		public void steacss(){
			AutoBase.open(Browser.Firefox, "http://localhost:3000/action");
			AutoBase.sElement(By.id("action-css")).click();
			AutoBase.sElement(By.cssSelector(".alert button")).click();
			AutoBase.sleep(3);
			AutoBase.closeAllWindow();
		}
		
		//@Test
		public void stea3css(){
			Auto.require("firefox");
			Auto.open("http://localhost:3000/action");
			Auto.currentpage().element(By.id("action-css")).click();
			//这是s-tea3.0里面的定位方式，jsoup的定位方式。
			Auto.currentpage().element(By.cssSelector(".alert button")).click();
			AutoBase.sleep(3);
			Auto.closeAllWindows();
		}
		//@Test
		public void link() throws InterruptedException{
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("http://localhost:3000/action");
			//点击开tagname元素定位的隐藏栏目
			driver.findElement(By.id("action-link")).click();
			driver.findElement(By.linkText("转向百度")).click();
			Thread.sleep(3000);
			driver.quit();
		}	
		
    //@Test
	public void stealink(){
		AutoBase.open(Browser.Firefox, "http://localhost:3000/action");
		AutoBase.sElement(By.id("action-link")).click();
		AutoBase.sElement(By.linkText("转向百度")).click();
		AutoBase.sleep(3);
		AutoBase.closeAllWindow();
	}
	@Test
	public void stea3link(){
		Auto.require("firefox");
		Auto.open("http://localhost:3000/action");
		Auto.currentpage().element(By.id("action-link")).click();
		Auto.currentpage().element(By.linkText("转向百度")).click();
		AutoBase.sleep(3);
		Auto.closeAllWindows();
	}
		
}
