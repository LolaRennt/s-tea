package org.sky.auto.test;

import org.junit.Test;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;

public class txtTest {
	
	@Test
	public void testTXT(){
		AutoBase.open(Browser.Firefox, "http://www.hao123.com");
		AutoBase.sElement("hao123百度logo").click();
		AutoBase.sleep(3);
		Window.selectNewWindow();
		AutoBase.sleep(3);
		AutoBase.sElement("百度-搜索框").sendKeys("北京");
		AutoBase.sElement("百度-搜索按钮").click();
		AutoBase.sleep(3);
		AutoBase.closeAllWindow();	
	}
	
//	public static void main(String[] args) {
//		WebDriver driver =new FirefoxDriver();
//		driver.get("http://www.hao123.com");
//		driver.findElement(By.className("js_logo-href")).click();
//		String handle = driver.getWindowHandle();
//		for(String h:driver.getWindowHandles()){
//			if(!h.equals(handle)){
//				driver.switchTo().window(h);
//			}
//		}
//		driver.findElement(By.id("kw")).sendKeys("北京");
//		driver.findElement(By.id("su")).click();
//		driver.close();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		driver.quit();
//	}

}
