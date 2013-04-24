package org.sky.auto.robot.key;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.sky.auto.base.AutoBase;
import org.sky.auto.intrumentation.ClassPool;
import org.sky.auto.page.source.Response;
import org.sky.auto.window.Window;

public class STeaKeywords {
	@RobotKeyword
	public void LogStart(){
		AutoBase.setLogStarted();
	}
	
	
	@RobotKeyword
	public void destoryWindows(){
		if(!AutoBase.isClose_Status()){
			AutoBase.closeAllWindow();
		}
	}
	
	@RobotKeyword
	public void waitForLoad(Integer s){
		AutoBase.setElementWaitTime(s);
	}
	
	@RobotKeyword
	public void pageExecuter(String PageName,String methodName,Object...objects){
		Set<Class<?>>cls=ClassPool.getClassPool();
		for(Class<?>clazz:cls){
			if(clazz.getName().trim().toLowerCase().equals(PageName.trim().toLowerCase())){
				Method[] methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					if(m.getName().toLowerCase().equals(methodName)){
						try {
							m.invoke(clazz.newInstance(),objects);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (InstantiationException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	

	@RobotKeyword
	public void elementClear(String id){
		AutoBase.sElement(id).clear();
	}
	
	@RobotKeyword
	public void elementClick(String id){
		AutoBase.sElement(id).click();
	}
	
	@RobotKeyword
	public void elementDoubleClick(String id){
		AutoBase.sElement(id).doubleClick();
	}
	
	@RobotKeyword
	public void elementDragAndDrop(String id,String oid){
		AutoBase.sElement(id).dragAndDrop(AutoBase.sElement(oid));
	}
	
	@RobotKeyword
	public void elementFocus(String id){
		AutoBase.sElement(id).focus();
	}
	
	@RobotKeyword
	public String elementGetAttribute(String id,String attr){
		return AutoBase.sElement(id).getAttribute(attr);
	}
	
	@RobotKeyword
	public String elementGetCssvalue(String id,String css){
		return AutoBase.sElement(id).getCssValue(css);
	}
	@RobotKeyword
	public Point elementGetLocation(String id){
		return AutoBase.sElement(id).getLocation();
	}
	
	@RobotKeyword
	public int[] elementGetSize(String id){
		return AutoBase.sElement(id).getSize();
	}
	
	@RobotKeyword
	public boolean elementIsDisplay(String id){
		return AutoBase.sElement(id).isDisplay();
	}
	
	@RobotKeyword
	public boolean elementIsEnable(String id){
		return AutoBase.sElement(id).isEnable();
	}
	
	@RobotKeyword
	public boolean elementIsExist(String id){
		return AutoBase.sElement(id).isExist();
	}
	
	@RobotKeyword
	public boolean elementIsSelect(String id){
		return AutoBase.sElement(id).isSelected();
	}
	
	
	@RobotKeyword
	public void elementLeftDown(String id){
	
		AutoBase.sElement(id).leftDown();
	}
	
	@RobotKeyword
	public void elementLeftUp(String id){
		AutoBase.sElement(id).leftUp();
	}
	
	@RobotKeyword
	public void elementKeyDown(String id,String key){
		Keys k = Enum.valueOf(Keys.class, key.toUpperCase().trim());
		AutoBase.sElement(id).keyDown(k);
	}

	@RobotKeyword
	public void elementKeyUp(String id,String key){
		Keys k = Enum.valueOf(Keys.class, key.toUpperCase().trim());
		AutoBase.sElement(id).keyUp(k);
	}
	@RobotKeyword
	public void elementMouseOver(String id){
		AutoBase.sElement(id).mouseOver();
	}
	@RobotKeyword
	public void elementInput(String id,String text){
		AutoBase.sElement(id).sendKeys(text);
	}
	@RobotKeyword
	public void elementScroll(String id){
		AutoBase.sElement(id).scroll();
	}
	
	@RobotKeyword
	public void Elementsubmit(String id){
		AutoBase.sElement(id).submit();
	}
	
	@RobotKeyword
	public int getComboBoxSize(String id){
		return AutoBase.comoboBox(id).getComoboBoxSize();
	}
	
	@RobotKeyword
	public String comboboxGetSelectedValue(String id){
		return AutoBase.comoboBox(id).getSelectedValue();
	}
	
	@RobotKeyword
	public int comboboxGetSelectedIndex(String id){
		return AutoBase.comoboBox(id).getSelectedIndex();
	}
	
	@RobotKeyword
	public void comboboxSelectByIndex(String id,int index){
		AutoBase.comoboBox(id).selectByIndex(index);
	}
	
	@RobotKeyword
	public void comboboxSelectByValue(String id,String value){
		AutoBase.comoboBox(id).selectByValue(value);
	}
	
	@RobotKeyword
	public void combooxSelectByVisiableTest(String id,String text){
		AutoBase.comoboBox(id).selectByVisiableText(text);
	}
	
	@RobotKeyword
	public void comboboxSelectByRandomIndex(String id){
		AutoBase.comoboBox(id).selectByRandomIndex();
	}

	@RobotKeyword
	public String tableGetTableContent(String id,Integer row,Integer col){
		return AutoBase.table(id).getTableContent(row, col);
	}
	
	@RobotKeyword
	public void openBrowser(String browser,String url){
		AutoBase.open(browser, url);
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
	
	@RobotKeyword
	public void WindowSelectNewOpenedWindow(){
		Window.selectNewWindow();
	}
	
//	@RobotKeyword
//	public long currentpageLoadTime(){
//		return AutoBase.currentpage().getLoadTime();
//	}
	
	@RobotKeyword
	public String currentpageHeaderValue(String name){
		return AutoBase.currentpage().getHeaderValue(name);
	}
	
	@RobotKeyword
	public long getJavaScriptLoadTime(String js){
		return AutoBase.currentpage().getJavaScriptLoadTime(js);
	}
	
	@RobotKeyword
	public List<String> getHeadCssLinks(){
		return AutoBase.currentpage().getHeadCssLinks();
	}
	
	@RobotKeyword
	public boolean currentpageisGzip(){
		return AutoBase.currentpage().isGzip();
	}
	
	@RobotKeyword
	public boolean isGzip(String url){
		Response response = new Response(url);
		return response.isGzip();
	}
}
