package org.sky.auto.window;

//import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.sky.auto.base.AutoBase;
import org.sky.auto.base.ConfigParser;
import org.sky.auto.driver.Browser;
import org.sky.auto.element.SElement;
import org.sky.auto.proxy.ProxyRunnerListener;


/**这个类把AutoBase解放出来，单独的定义为Window类，里面封装的都是浏览器自身的一些基本操作。比较全的方法
 * @author 王天庆
 * */
public class Window {
	private static Set<String> windowHandles =new HashSet<String>();	
	/**通过title来选择页面*/
	public static void selectWindowByTitle(String title){
		ProxyRunnerListener.getDispatcher().beforeselectWindow();
		Set<String> windows = AutoBase.driver().getWindowHandles();
		for(String window : windows){
			AutoBase.driver().switchTo().window(window);
			String titleName = AutoBase.driver().getTitle();
			if(titleName.equals(title)){
				break;
			}
		}
		ProxyRunnerListener.getDispatcher().afterselectWindow();
	}
	
	/**通过title的一部分来选择window页面*/
	public static void selectWindowContainTitle(String title){
		ProxyRunnerListener.getDispatcher().beforeselectWindow();
		Set<String> windows = AutoBase.driver().getWindowHandles();
		for(String window : windows){
			AutoBase.driver().switchTo().window(window);
			String titleName = AutoBase.driver().getTitle();
			if(titleName.contains(title)){
				break;
			}
		}
		ProxyRunnerListener.getDispatcher().beforeselectWindow();
	}
	
	public static void selectWindowByUrl(String url){
		ProxyRunnerListener.getDispatcher().beforeselectWindow();
		Set<String> windows = AutoBase.driver().getWindowHandles();
		for(String window : windows){
			AutoBase.driver().switchTo().window(window);
			String titleName = AutoBase.driver().getCurrentUrl();
			if(titleName.equals(url)){
				break;
			}
		}
		ProxyRunnerListener.getDispatcher().afterselectWindow();
	}
	
	/**通过url的一部分来选择window页面*/
	public static void selectWindowContainUrl(String url){
		ProxyRunnerListener.getDispatcher().beforeselectWindow();
		Set<String> windows = AutoBase.driver().getWindowHandles();
		for(String window : windows){
			AutoBase.driver().switchTo().window(window);
			String titleName = AutoBase.driver().getCurrentUrl();
			if(titleName.contains(url)){
				break;
			}
		}
		ProxyRunnerListener.getDispatcher().afterselectWindow();
	}
	/**进入frame*/
	public static void selectFrame(By selector){
		AutoBase.driver().switchTo().frame(AutoBase.driver().findElement(selector));
	}
	/**进入frame*/
	public static void selectFrameByName(String name){
		AutoBase.driver().switchTo().frame(name);
	}
	/**返回默认的window下*/
	public static void selectDefaultWindow(){
		AutoBase.driver().switchTo().defaultContent();
	}
	/**进入frame里面*/
	public static void selectFrame(WebElement element){
		AutoBase.driver().switchTo().frame(element);
	}
	
	public static void selectFrame(SElement element){
		AutoBase.driver().switchTo().frame(element.getElement());
	}
	
	/**执行js*/
	public static Object runJS(String js){
		ProxyRunnerListener.getDispatcher().beforerunJS();
		Object obj= ((JavascriptExecutor)AutoBase.driver()).executeScript(js);
		ProxyRunnerListener.getDispatcher().afterrunJS();
		return obj;
	}
	/**执行带有参数的js*/
	public static Object runJs(String js, Object... o){
		ProxyRunnerListener.getDispatcher().beforerunJS();
		Object obj=((JavascriptExecutor)AutoBase.driver()).executeScript(js, o);
		ProxyRunnerListener.getDispatcher().afterrunJS();
		return obj;
	}
	/**关闭所有的窗口*/
	public static void closeAllWindows(){
		ProxyRunnerListener.getDispatcher().beforecloseAllWindows();
		AutoBase.driver().quit();
		ProxyRunnerListener.getDispatcher().aftercloseAllWindows();
	}
	/**关闭当前窗口*/
	public static void closeCurrentWindow(){
		ProxyRunnerListener.getDispatcher().beforecloseCurrentWindow();
		AutoBase.driver().close();
		ProxyRunnerListener.getDispatcher().aftercloseCurrentWindow();
	}
	/**滚动到指定的元素的位置*/
	public static void scrollTo(WebElement element){
		ProxyRunnerListener.getDispatcher().beforescrollTo();
		Point point = element.getLocation();
	    int x =point.getX();
	    int y =point.getY();
	    ((JavascriptExecutor)AutoBase.driver()).executeScript("window.scrollTo("+x+","+y+")");
	    ProxyRunnerListener.getDispatcher().afterscrollTo();
	}
	/**最大化窗口*/
	public static void maxWindow(){
//		int screenX=Toolkit.getDefaultToolkit().getScreenSize().width;
//	    int screenY=Toolkit.getDefaultToolkit().getScreenSize().height;
//	    //最大化浏览器的实现代码
//	    ((WebDriver)AutoBase.driver()).manage().window().setSize(new Dimension(screenX,screenY));
		ProxyRunnerListener.getDispatcher().beforemaxWindow();
		if(AutoBase.driver() instanceof HtmlUnitDriver){
			//do nothing！
		}else{
			AutoBase.driver().manage().window().maximize();
		}
		
		ProxyRunnerListener.getDispatcher().aftermaxWindow();
	}
	/**页面刷新*/
	public static void refresh(){
		ProxyRunnerListener.getDispatcher().beforerefresh();
		AutoBase.driver().navigate().refresh();
		ProxyRunnerListener.getDispatcher().afterrefresh();
	}
	
	/**浏览器后退*/
	public static void forward(){
		ProxyRunnerListener.getDispatcher().beforeforward();
		AutoBase.driver().navigate().forward();
		ProxyRunnerListener.getDispatcher().afterforward();
	}
	/**浏览器前进*/
	public static void back(){
		ProxyRunnerListener.getDispatcher().beforeback();
		AutoBase.driver().navigate().back();
		ProxyRunnerListener.getDispatcher().afterback();
	}
	
	/**得到当前窗口下的title*/
	public static String getTitle(){
		return AutoBase.driver().getTitle();
	}
	/**截屏操作,将当前页面的截屏放在指定的目录下*/
	public static void takeScreenShot(){
		ProxyRunnerListener.getDispatcher().beforetakeScreenShot();
		String path = ConfigParser.getScreenShotPath();
		TakesScreenshot tss = (TakesScreenshot)AutoBase.driver();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String time = sdf.format(new Date());
		File file = tss.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(path+File.separator+time+"png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ProxyRunnerListener.getDispatcher().aftertakeScreenShot();
	}
	/**处理alert窗口*/
	public static String dealAlert() {
		ProxyRunnerListener.getDispatcher().beforedealAlert();
		String alertMessage=null;
		
		try{
			Alert alert=AutoBase.driver().switchTo().alert();
			alertMessage= alert.getText();
			alert.accept();
			ProxyRunnerListener.getDispatcher().afterdealAlert();
			return alertMessage;
		}catch (Exception e) {
			System.out.println("没有找到Alert窗口!");
		}
		return null;
	}
	/**处理Prompt窗口*/
	public static String dealPrompt(String input,boolean isYes) {
		String promptMessage=null;
		try{
			Alert alert=AutoBase.driver().switchTo().alert();
			alert.sendKeys(input);
			promptMessage= alert.getText();
			if(isYes)
				alert.accept();
			else 
				alert.dismiss();
			return promptMessage;
		}catch (Exception e) {
			System.out.println("没有找到Prompt窗口!");
		}return null;
	}
	/**处理confirm窗口*/
	public static String dealConfirm(boolean isYes) {
		ProxyRunnerListener.getDispatcher().beforedealConfirm();
		String confirmMessage=null;
		try{
			Alert alert=AutoBase.driver().switchTo().alert();
			confirmMessage=alert.getText();
			if(isYes)alert.accept();
			else alert.dismiss();
			alert.accept();
			ProxyRunnerListener.getDispatcher().afterdealConfirm();
			return confirmMessage;
		}catch (Exception e) {
			System.out.println("没有找到Prompt窗口!");
		}return null;
	}
	
	/**初始化窗口，指定浏览器*/
	public static void open(Browser browser,String url){
	//	ProxyRunnerListener.getDispatcher().beforeOpen();
		AutoBase.open(browser, url);
	//	ProxyRunnerListener.getDispatcher().afterOpen();
	}
	/**跳转页面到某个链接*/
	public static void open(String url){
	//	ProxyRunnerListener.getDispatcher().beforeOpen();
		AutoBase.open(url);
	//	ProxyRunnerListener.getDispatcher().afterOpen();
	}
	public static void scrollTo(SElement element){
		ProxyRunnerListener.getDispatcher().beforescrollTo();
		scrollTo(element.getElement());
		ProxyRunnerListener.getDispatcher().afterscrollTo();
	}
	
	public static String getUrl(){
		return AutoBase.driver().getCurrentUrl();
	}
	/**更新当前所有的窗口集合*/
	public static void updateWindow(){
		windowHandles=AutoBase.driver().getWindowHandles();
	}
	/**切换到最新打开的窗口*/
	public static void selectNewWindow(){
		for(String window:AutoBase.driver().getWindowHandles()){
			int i=0;
			for(String wh:windowHandles){
				if(wh.equals(window)){
					i++;
				}
			}
			if(i==0){
				AutoBase.driver().switchTo().window(window);
				break;
			}
		}
	}
	
	/**清空句柄集合*/
	public static void clearWindowHandle(){
		windowHandles.clear();
	}
	
	
	
	
	
}
