package org.sky.auto.base;

//import org.sky.auto.basetools.ConfigParser;
import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestNGRemoteBase extends ITestNGBase{
	
	static Logger logger = Logger.getLogger(TestNGRemoteBase.class);
	/**提前把浏览器的路径加载到path中*/
	@Parameters({"browser"})
	@BeforeMethod
	public void setUp(String browser) {
		PropertyConfigurator.configure("resource"+File.separator+"log4j.properties");
		if(browser.equals("ie")){
			AutoBase.setDriver(Browser.IE);
		}else if(browser.equals("firefox")){
			AutoBase.setDriver(Browser.Firefox);
			//System.out.println("打印成功！");
		}else if(browser.equals("chrome")){
			AutoBase.setDriver(Browser.Chrome);
		}else if(browser.equals("htmlUnit")){
			AutoBase.setDriver(Browser.HtmlUnit);
		}else if(browser.equals("safari")){
			AutoBase.setDriver(Browser.Safari);
		}else if(browser.equals("remote-ie")){
			AutoBase.setDriver(Browser.RemoteIE);
		}else if(browser.equals("remote-firefox")){
			AutoBase.setDriver(Browser.RemoteFirefox);
		}else if(browser.equals("remote-chrome")){
			AutoBase.setDriver(Browser.RemoteChrome);
		}else if(browser.equals("remote-safari")){
			AutoBase.setDriver(Browser.RemoteSafari);
		}else if(browser.equals("remote-htmlUnit")){
			AutoBase.setDriver(Browser.RemoteHtmlUnit);
		}else{
			throw new UnsupportedOperationException("提供的测试所需要的浏览器信息错误！！");
		}
		logger.info("多线程自动化初始化成功！选择浏览器"+browser+"进行测试！");
	}

	@AfterMethod
	public void destory() {
		Window.closeAllWindows();
		//AutoBase.clearThread();
		logger.info("自动化测试Case结束，资源释放成功！");
	}

	@Override
	@BeforeMethod
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

}
