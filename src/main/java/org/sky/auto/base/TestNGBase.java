package org.sky.auto.base;


import org.apache.log4j.Logger;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestNGBase extends ITestNGBase{
	static Logger logger = Logger.getLogger(TestNGBase.class);
	@BeforeMethod
	public void setUp(){
		AutoBase.setLogStarted();
		logger.info("自动化测试初始化成功！日志默认配置成功！");
	}
	
	
	
	@AfterMethod
	public void destory(){
		Window.closeAllWindows();
		//DriverHandler.setHandler(null);
		AutoBase.getAutoDriver().setDriver(Browser.NULL);
		logger.info("自动化测试Case结束，资源释放成功！");
	}
}
