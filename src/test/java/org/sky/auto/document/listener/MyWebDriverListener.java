package org.sky.auto.document.listener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sky.auto.driver.event.AutoDriverEventListener;

public class MyWebDriverListener extends AutoDriverEventListener {
	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("我点击成功了！发现了木有呢？");
	}
}
