package org.sky.auto.robot.key;


import org.openqa.selenium.Keys;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.window.Window;


@RobotKeywords
public class WindowKeywords{
	
	@RobotKeyword
	public void openBrowser(String browser,String url){
		Browser b=Enum.valueOf(Browser.class, browser);
		AutoBase.open(b, url);
	}
	
	@RobotKeyword
	public void closeAllWindow(){
		AutoBase.closeAllWindow();
	}
	
	@RobotKeyword
	public void closeCurrentWindow(){
		AutoBase.closeCurrentWindow();
	}
	
	@RobotKeyword
	public void WindowscrollTo(String id){
		Window.scrollTo(AutoBase.element(id));
	}
	
	@RobotKeyword
	public void WindowBack(){
		Window.back();
	}
	
	@RobotKeyword
	public void WindowForward(){
		Window.forward();
	}
	
	@RobotKeyword
	public void WindowRefresh(){
		Window.refresh();
	}
	
	@RobotKeyword
	public void WindowRunJavaScript(String js){
		Window.runJS(js);
	}
	
	@RobotKeyword
	public void WindowRunJavaScript(String js,Object...objects ){
		Window.runJs(js, objects);
	}
	
	@RobotKeyword
	public void WindowMaxWindow(){
		Window.maxWindow();
	}
	
	@RobotKeyword
	public String WindowGetTitle(){
		return Window.getTitle();
	}
	
	@RobotKeyword
	public String WindowCurrentUrl(){
		return Window.getTitle();
	}
	
	@RobotKeyword
	public void SelectDefaultWindow(){
		Window.selectDefaultWindow();
	}
	
	@RobotKeyword
	public void SelectWindowByTitle(String title){
		Window.selectWindowByTitle(title);
	}
	
	@RobotKeyword
	public void SelectWindowByUrl(String url){
		Window.selectWindowByUrl(url);
	}
	
	@RobotKeyword
	public void SelectWindowContainTitle(String title){
		Window.selectWindowContainTitle(title);
	}
	
	@RobotKeyword
	public void SelectWindowContainUrl(String url){
		Window.selectWindowContainUrl(url);
	}
	
	@RobotKeyword
	public void dealAerlt(){
		Window.dealAlert();
	}
	
	@RobotKeyword
	public void takeScreenShot(){
		Window.takeScreenShot();
	}
	
	@RobotKeyword
	public void WindowkeyDown(String key){
		Keys k = Enum.valueOf(Keys.class, key.toUpperCase().trim());
		AutoBase.keyDown(k);
	}
	
	@RobotKeyword
	public void WindowkeyUp(String key){
		Keys k = Enum.valueOf(Keys.class, key.toUpperCase().trim());
		AutoBase.keyUp(k);
	}
	
}
