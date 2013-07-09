package org.sky.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocatorTest {
	
	@Test
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

}
