package org.sky.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;

import com.github.lmm.core.Auto;


public class ActionTest {
	
	//@Test
	public void keyEnter() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		driver.findElement(By.id("kw")).sendKeys("北京");
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
		Thread.sleep(3000);
		driver.quit();
	}
	
	
	//@Test
	public void mouseOverTest() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://www.it168.com");
		Actions actions = new Actions(driver);
		WebElement bijiben=driver.findElement(By.xpath(".//*[@id='h80']/dt/i[1]/a[1]"));
		actions.moveToElement(bijiben).build().perform();
		driver.findElement(By.xpath(".//*[@id='h80']/dt/div/span[1]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		driver.quit();
	}
	
	//@Test
	public void steamouseover(){
		AutoBase.open(Browser.Firefox, "http://www.it168.com");
		AutoBase.sElement(By.xpath(".//*[@id='h80']/dt/i[1]/a[1]")).mouseOver();
		AutoBase.sElement(By.xpath(".//*[@id='h80']/dt/div/span[1]/ul/li[1]/a")).click();
		AutoBase.sleep(4);
		AutoBase.closeAllWindow();
	}
	
	//@Test
	public void stea3mouseOver(){
		Auto.require("firefox");
		Auto.open("http://it168.com");
		Auto.currentpage().element(By.xpath(".//*[@id='h80']/dt/i[1]/a[1]")).mouseOver();
		Auto.currentpage().element(By.xpath(".//*[@id='h80']/dt/i[1]/a[1]")).scroll();
		AutoBase.sleep(3);
		Auto.currentpage().element(By.xpath(".//*[@id='h80']/dt/div/span[1]/ul/li[1]/a")).click();
		AutoBase.sleep(3);
		Auto.closeAllWindows();
	}
	
	
	//@Test
	public void steakeytest(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		AutoBase.sElement(By.id("kw")).sendKeys("北京");
		AutoBase.keypress(Keys.ENTER);
		AutoBase.sleep(3);
		AutoBase.closeAllWindow();
	}
	
	//@Test
	public void stea3keytest(){
		Auto.require("firefox");
		Auto.open("http://www.baidu.com");
		Auto.currentpage().element(By.id("kw")).input("北京");
		Auto.currentpage().keypress(Keys.ENTER);
		AutoBase.sleep(3);
		Auto.closeAllWindows();
	}
	
	//@Test
	public void frametest() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://lesson.ibiubiu.org");
		driver.findElement(By.xpath(".//*[@id='nav-left']/li[4]/a")).click();
		Thread.sleep(4000);
		//frame页面加载完毕，我们把driver引入到frame内部
		/***通过frame的索引进入到frame中
		 * <code>
		 * driver.switchTo().frame(0);
		 * </code>
		 * 通过id或者name属性进入frame中
		 * <code>driver.switchTo().frame("frame_case");
		 * 我们还可以通过定位元素的方式进入frame
		 * <code>
		 * driver.switchTo().frame(driver.findElement(By.id("frame_case")));
		 * </code>
		 * */
		driver.switchTo().frame(0);
		driver.findElement(By.id("case-button")).click();
		//关闭模态窗口
		driver.findElement(By.id("model-close")).click();
		driver.quit();
		
	}
	
	//@Test
	public void steaFrameTest(){
		AutoBase.open(Browser.Firefox, "http://lesson.ibiubiu.org");
		AutoBase.sElement(By.xpath(".//*[@id='nav-left']/li[4]/a")).click();
		AutoBase.sleep(4);
		Window.selectFrame(0);
		AutoBase.sElement(By.id("case-button")).click();
		//关闭模态窗口
		AutoBase.sElement(By.id("model-close")).click();
		AutoBase.closeAllWindow();
	}
	
	
	@Test
	public void stea3FrameTest(){
		Auto.require("firefox");
		Auto.open("http://lesson.ibiubiu.org");
		Auto.currentpage().element(By.xpath(".//*[@id='nav-left']/li[4]/a")).click();
		AutoBase.sleep(4);
		Auto.currentpage().frame(0).element(By.id("case-button")).click();
		Auto.currentpage().frame(0).element(By.id("model-close")).click();
		Auto.closeAllWindows();
	}
	
}
