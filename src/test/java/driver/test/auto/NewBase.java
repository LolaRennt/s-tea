package driver.test.auto;

import org.openqa.selenium.WebDriver;
import org.sky.auto.driver.Browser;

public class NewBase {
	
	static private  NewDriver driver;
	
	public static NewDriver getDriver() {
		return driver;
	}

	public static  void setDriver(NewDriver driver) {
		NewBase.driver = driver;
	}
	
	public static WebDriver driver(){
		return driver.getDriver();
	}
	
	public static  void open(Browser browser,String url){
		driver.setDriver(Browser.Firefox);
		driver().get(url);
	}
}
