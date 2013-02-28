package org.sky.auto.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.sky.auto.base.ConfigParser;
import org.sky.auto.driver.event.AutoDriverEventListener;
import org.sky.auto.runner.AutoResetThreadLocal;


public class AutoDriver implements IDriver{
	static Logger logger = Logger.getLogger(AutoDriver.class);
	//ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();
	private WebDriver driver;
	private EventFiringWebDriver efdriver;
//	volatile static AutoDriver td;
//	public static AutoDriver getInstance(){
//		if(td==null){
//			synchronized (AutoDriver.class) {
//				if(td==null){
//					td= new AutoDriver();
//				}
//			}
//		}
//		return td;
//	}

	class Firefox implements Runnable{
		AutoResetThreadLocal<FirefoxDriver> atl = new AutoResetThreadLocal<FirefoxDriver>(){
			
			protected synchronized FirefoxDriver initialValue() {
				//setDriver(Browser.NULL);
				return new FirefoxDriver();
			}
		};

		
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		public  FirefoxDriver firefox(){
			return atl.get();
		}
	}
	
	class IE implements Runnable{
		ThreadLocal<InternetExplorerDriver> tl = new ThreadLocal<InternetExplorerDriver>(){
			
			protected InternetExplorerDriver initialValue() {
				return new InternetExplorerDriver();
			};
		};
		
		public void run() {
			
		}
		public InternetExplorerDriver ie(){
			return tl.get();
		}
	}
	
	class Safari implements Runnable{
		ThreadLocal<SafariDriver> tl = new ThreadLocal<SafariDriver>(){
			protected SafariDriver initialValue() {
				return new SafariDriver();
			};
		};

		
		public void run() {
			
		}
		public SafariDriver safari(){
			return tl.get();
		}
	}
	
	class Chrome implements Runnable{
		ThreadLocal<ChromeDriver> tl = new ThreadLocal<ChromeDriver>(){
			protected ChromeDriver initialValue() {
				return new ChromeDriver();
			};
		};
	

		
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		public ChromeDriver chrome(){
			return tl.get();
		}
	}
	
	class HtmlUnit implements Runnable{
		ThreadLocal<HtmlUnitDriver> tl = new ThreadLocal<HtmlUnitDriver>(){
			protected HtmlUnitDriver initialValue() {
				return new HtmlUnitDriver();
			};
		};

		
		public void run() {
		
		}
		public HtmlUnitDriver htmlUnit(){
			return tl.get();
		}
		
	}
	
	class RemoteFirefox implements Runnable{

		
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		ThreadLocal<RemoteWebDriver> tl = new ThreadLocal<RemoteWebDriver>(){
			
			protected RemoteWebDriver initialValue() {
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getFirefoxNode()), DesiredCapabilities.firefox());
				} catch (MalformedURLException e) {
					logger.error("实例化url出错，检查一下url格式是否正确，格式为：http://192.168.1.1:4444");
					e.printStackTrace();
				}
				return null;		
			}
		};
		
		public RemoteWebDriver remoteFirefox(){
			return tl.get();
		}
	}
	
	class RemoteIE implements Runnable{

		
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		ThreadLocal<RemoteWebDriver> tl = new ThreadLocal<RemoteWebDriver>(){
			
			protected RemoteWebDriver initialValue() {
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getIENode()), DesiredCapabilities.internetExplorer());
				} catch (MalformedURLException e) {
					logger.error("实例化url出错，检查一下url格式是否正确，格式为：http://192.168.1.1:4444");
					e.printStackTrace();
				}
				return null;		
			}
		};
		
		public RemoteWebDriver remoteIE(){
			return tl.get();
		}
	}
	
	class RemoteChrome implements Runnable{

		
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		ThreadLocal<RemoteWebDriver> tl = new ThreadLocal<RemoteWebDriver>(){
			
			protected RemoteWebDriver initialValue() {
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getChromeNode()), DesiredCapabilities.chrome());
				} catch (MalformedURLException e) {
					logger.error("实例化url出错，检查一下url格式是否正确，格式为：http://192.168.1.1:4444");
					e.printStackTrace();
				}
				return null;		
			}
		};
		
		public RemoteWebDriver remoteChrome(){
			return tl.get();
		}
	}
	
	class RemoteSafari implements Runnable{

		
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		ThreadLocal<RemoteWebDriver> tl = new ThreadLocal<RemoteWebDriver>(){
			
			protected RemoteWebDriver initialValue() {
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getSafariNode()), DesiredCapabilities.safari());
				} catch (MalformedURLException e) {
					logger.error("实例化url出错，检查一下url格式是否正确，格式为：http://192.168.1.1:4444");
					e.printStackTrace();
				}
				return null;		
			}
		};
		
		public RemoteWebDriver remoteSafari(){
			return tl.get();
		}
	}
	
	class RemoteHtmlUnit implements Runnable{

		
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		ThreadLocal<RemoteWebDriver> tl = new ThreadLocal<RemoteWebDriver>(){
			
			protected RemoteWebDriver initialValue() {
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getHtmlUnitNode()), DesiredCapabilities.htmlUnit());
				} catch (MalformedURLException e) {
					logger.error("实例化url出错，检查一下url格式是否正确，格式为：http://192.168.1.1:4444");
					e.printStackTrace();
				}
				return null;		
			}
		};
		
		public RemoteWebDriver remoteHtmlUnit(){
			return tl.get();
		}
	}
	
	
	public WebDriver getDriver() {
		return this.driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriver(Browser browser){
		if(browser.equals(Browser.Firefox)){
			Firefox ff = new Firefox();
			setDriver(ff.firefox());
		}else if(browser.equals(Browser.Chrome)){
			Chrome c = new Chrome();
			setDriver(c.chrome());
		}else if(browser.equals(Browser.IE)){
			IE ie = new IE();
			setDriver(ie.ie());
		}else if(browser.equals(Browser.Safari)){
			Safari s = new Safari();
			setDriver(s.safari());
		}else if(browser.equals(Browser.RemoteChrome)){
			RemoteChrome rc = new RemoteChrome();
			setDriver(rc.remoteChrome());
		}else if(browser.equals(Browser.RemoteFirefox)){
			RemoteFirefox rf = new RemoteFirefox();
			setDriver(rf.remoteFirefox());
		}else if(browser.equals(Browser.RemoteIE)){
			RemoteIE ri = new RemoteIE();
			setDriver(ri.remoteIE());
		}else if(browser.equals(Browser.RemoteHtmlUnit)){
			RemoteHtmlUnit rh = new RemoteHtmlUnit();
			setDriver(rh.remoteHtmlUnit());
		}else if(browser.equals(Browser.RemoteSafari)){
			RemoteSafari rs = new RemoteSafari();
			setDriver(rs.remoteSafari());
		}else if(browser.equals(Browser.HtmlUnit)){
			HtmlUnit hu = new HtmlUnit();
			setDriver(hu.htmlUnit());
		}else if(browser.equals(Browser.NULL)){
			this.driver=null;
		}else{
			logger.error("请输入正确的浏览器模式，否则无法正确的运行程序！");
		}
	}

	
	public WebDriver getWebDriver() {
		// TODO Auto-generated method stub
		return driver;
	}
	
	public WebDriver register(AutoDriverEventListener ade){
		driver = new EventFiringWebDriver(getWebDriver()).register(ade);	
		return driver;
	}

	public EventFiringWebDriver getEfdriver() {
		return efdriver;
	}

	public void setEfdriver(EventFiringWebDriver efdriver) {
		this.efdriver = efdriver;
	}
	
	public WebDriver unregister(AutoDriverEventListener ade){
		driver = new EventFiringWebDriver(getWebDriver()).unregister(ade);
		return driver;
	}
}
