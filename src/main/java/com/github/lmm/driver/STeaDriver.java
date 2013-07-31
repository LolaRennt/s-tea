package com.github.lmm.driver;

import java.io.Serializable;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.opera.core.systems.OperaDriver;

public abstract class STeaDriver implements WebDriver, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static class STeaFirefoxDriver extends FirefoxDriver implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = -205384183818081797L;
		
		
		
	}
	
	public static class STeaChromeDriver extends ChromeDriver implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = -4102891812054949781L;
		
	}
	
	public static class STeaHtmlUnitDriver extends HtmlUnitDriver implements Serializable{

		public STeaHtmlUnitDriver(BrowserVersion version) {
			super(version);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;	
	}

	public static class STeaIEDriver extends InternetExplorerDriver implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = -6257683223338575153L;
		
	}
	
	public static class STeaSafariDriver extends SafariDriver implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
	public static class STeaPhantomJSDriver extends PhantomJSDriver implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public STeaPhantomJSDriver(Capabilities desiredCapabilities) {
			super(desiredCapabilities);
			// TODO Auto-generated constructor stub
		}
		public STeaPhantomJSDriver(PhantomJSDriverService service,Capabilities desiredCapabilities) {
			super(service,desiredCapabilities);
		}
		
	}
	
	public static class STeaOperaDriver extends OperaDriver implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
	public static class STeaRemoteWebDriver extends RemoteWebDriver implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public STeaRemoteWebDriver() {
			// TODO Auto-generated constructor stub
		}
		public STeaRemoteWebDriver(URL url, DesiredCapabilities remote) {
			super(url,remote);
		}
		public STeaRemoteWebDriver(URL url, DesiredCapabilities dc,
				DesiredCapabilities desiredCapabilities) {
			super(url,dc,desiredCapabilities);
		}
	}
}
