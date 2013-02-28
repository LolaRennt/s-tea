package driver.test.auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.sky.auto.driver.Browser;

public class NewDriver {
	ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();
	private WebDriver driver;
	
	
	 class Firefox implements Runnable{

		public FirefoxDriver getInstance(){
			return new FirefoxDriver();		
		}
		@Override
		public void run() {
			
		}
		
	}
	
	public synchronized WebDriver firefox(){
		Firefox ff = new Firefox();
		tl.set(ff.getInstance());
		return tl.get();
	}
	
	
	public void setDriver(Browser browser){
		this.driver=firefox();
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
	
}
