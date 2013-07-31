package org.sky.example;

import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;

import com.github.lmm.core.Auto;

public class WindowTest {

	//@Test
	public void  windowTest() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:3000");
		//获取到当前的句柄
		String currentWindowHandle = driver.getWindowHandle();
		driver.findElement(By.xpath(".//*[@id='nav-left']/li[4]/a")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("openbaidu")).click();
		//收集当前页面所有的句柄，并且删除掉最开始打开的句柄，这样剩下一个句柄存在set中
		Set<String> handles=driver.getWindowHandles();
		handles.remove(currentWindowHandle);
		//切换句柄到百度首页
		driver.switchTo().window(handles.iterator().next());
		//对百度首页进行查询操作。
		driver.findElement(By.id("kw")).sendKeys("百度首页-搜索按钮");
		driver.findElement(By.id("su")).click();
		Thread.sleep(4000);
		driver.quit();
	}
	
	@Test
	public void steawindowtest(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		//AutoBase.currentpage().sElement(By.id("kw")).sendKeys("北京");
		//AutoBase.currentpage().sElement(By.id("su")).click();
		AutoBase.openNew().open("http://www.hao123.com");
		System.out.println(AutoBase.currentpage().getTitle());
		AutoBase.closeAllWindow();
		
	}
	
	//@Test
	public void stea3windowtest(){
		Auto.require("firefox");
		Auto.open("http://localhost:3000");
		Auto.currentpage().element(By.xpath(".//*[@id='nav-left']/li[4]/a")).click();
		AutoBase.sleep(4);
		Auto.selectLastOpenedPage();
		Auto.currentpage().element(By.id("kw")).input("北京");
		Auto.currentpage().element(By.id("su")).click();
		AutoBase.sleep(4);
		Auto.closeAllWindows();
	}
}
