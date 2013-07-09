package org.sky.example;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * Firefox浏览器打开的操作，其实就是对firefox路径查找的操作,一般情况下，我们的firefox在系统路径下就可以使用默认的打开方式,
 * 我推荐尽量在环境上改善firefox的打开代码，尽量使用默认的打开方法。
 * */
public class FirefoxOpen {
	/**这个方法是在firefox默认路径下的打开方式*/
	public void defaultOpen(){
		FirefoxDriver firefox = new FirefoxDriver();
		firefox.close();
	}
	/**这个方法演示的是打开带有指定插件的firefox的方法
	 * 关于firefox浏览器的preference的配置，需要我们在浏览器地址栏输入about:config
	 * 然后在里面查找相关配置。
	 * @throws IOException 
	 * */
	public void profileOpen() throws IOException{
		FirefoxProfile firefoxprofile = new FirefoxProfile();
		firefoxprofile.addExtension(new File("firebug.xpi")); 
		firefoxprofile.setPreference("extensions.firebug.currentVersion", "1.11");
		FirefoxDriver firefox = new FirefoxDriver(firefoxprofile);
		firefox.close();
	}

	/**
	 * 当我们的浏览器不在默认的目录的时候，我们需要指定一下浏览器的路径可以通过下面的几种方式来解决
	 * 在path中加入webdriver.firefox.bin的路径
	 * */
	public void pathopen(){
		System.setProperty("webdriver.firefox.bin", "D:\\firefox\\firefox.exe");
		FirefoxDriver firefox= new FirefoxDriver();
		firefox.close();
	}
	
	/**
	 * 通过firefox的二进制文件路径来加载firefox
	 * */
	public void open(){
		FirefoxBinary firefoxBinary = new FirefoxBinary(new File("Your Firefox Path"));
		FirefoxDriver firefox = new FirefoxDriver(firefoxBinary,null);
		firefox.close();
	}
	
	/**
	 * 通过Capabilities来配置firefox
	 * */
	public void capabilitiesopen(){
		DesiredCapabilities des = DesiredCapabilities.firefox();
		des.setCapability("firefox_binary", "Your Firefox Path");
		FirefoxDriver firefox = new FirefoxDriver(des);	
		firefox.close();
	}
	
}
