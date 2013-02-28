package org.sky.auto.story.action;

import org.sky.auto.base.AutoBase;
import org.sky.auto.element.SElement;
import org.sky.auto.window.Window;

public class Actions {
	private SElement element;
	public void setElement(SElement element){
		this.element=element;
	}
	public void simpleAction(String action){
		if(KeyActions.ACTION_CLEAR.equals(action)){
			element.clear();
		}else if(KeyActions.ACTION_CLICK.equals(action)){
			element.click();
		}else if(KeyActions.ACTION_CLOSE.equals(action)){
			Window.closeCurrentWindow();
		}else if(KeyActions.ACTION_DOUBLE_CLICK.equals(action)){
			element.doubleClick();
		}else if(KeyActions.ACTION_FOCUS.equals(action)){
			element.focus();
		}else if(KeyActions.ACTION_MOVEOVER.equals(action)){
			element.mouseOver();
		}else if(KeyActions.ACTION_LEFT_DOWN.equals(action)){
			element.leftDown();
		}else if(KeyActions.ACTION_LEFT_UP.equals(action)){
			element.leftUp();
		}else if(KeyActions.WINDOW_SCROLL.equals(action)){
			element.scroll();
		}else if(KeyActions.WINDOW_ALERT_ACCPET.equals(action)){
			Window.dealAlert();
		}else if(KeyActions.WINDOW_BACK.equals(action)){
			Window.back();
		}else if(KeyActions.WINDOW_REFRESH.equals(action)){
			Window.refresh();
		}else if(KeyActions.WINDOW_FORWARD.equals(action)){
			Window.forward();
		}else if(KeyActions.WINDOW_DEFAULT_WINDOW.equals(action)){
			Window.selectDefaultWindow();
		}else if(KeyActions.WINDOW_MAX.equals(action)){
			Window.maxWindow();
		}else if(KeyActions.WINDOW_SCREEN_SHOT.equals(action)){
			Window.takeScreenShot();
		}else if(KeyActions.WINDOW_BACK.equals(action)){
			Window.back();
		}else if(KeyActions.ACTION_CLOSE_ALL.equals(action)){
			Window.closeAllWindows();
		}
	}
	
	public String getTitleAction(String action){
		if(KeyActions.WINDOW_TITLE.equals(action)){
			return AutoBase.driver().getTitle();
		}else{
			return null;
		}
	}
	
	public String getTextAction(String action){
		if(KeyActions.WINDOW_TEXT.equals(action)){
			return element.getText();
		}
		return null;
	}
	
	public String getAttributeAction(String action,String attr){
		if(KeyActions.WINDOW_ATTRIBUTE.equals(action)){
			return element.getAttribute(attr);
		}
		return null;
	}
	
	public String getCssAction(String action,String name){
		if(KeyActions.WINDOW_CSS.equals(action)){
			return element.getCssValue(name);
		}
		return name;
	}
	
	public String getTagNameAction(String action){
		if(KeyActions.WINDOW_TAGNAME.equals(action)){
			return element.getTagName();
		}
		return null;
	}
	

	
	public String elementDisplayStatus(){
		if(element.isDisplay()){
			return "可见的";
		}else{
			return "不可见的";
		}
	}
	
	public String elementEditStatus(){
		if(element.isEnable()){
			return "可编辑的";
		}else{
			return "不可编辑的";
		}
	}
	
	public String elementExistStatus(){
		if(element.isExist()){
			return "存在";
		}else{
			return "不存在";
		}
	}
	
	public String elementSelectedStatus(){
		if(element.isSelected()){
			return "可选择";
		}else{
			return "不可选择";
		}
	}
	
	public String normalAction(String action,String...strings){
		if(action.equals(KeyActions.WINDOW_TITLE)){
			return getTitleAction(action);
		}else if(action.equals(KeyActions.WINDOW_TEXT)){
			return getTextAction(action);
		}else if(action.equals(KeyActions.WINDOW_TAGNAME)){
			return getTagNameAction(action);
		}else if(action.equals(KeyActions.WINDOW_CSS)){
			return getCssAction(action, strings[0]);
		}else if(action.equals("可见性")){
			return elementDisplayStatus();
		}else if(action.equals("存在性")){
			return elementExistStatus();
		}else if(action.equals("编辑性")){
			return elementEditStatus();
		}else if(action.equals(KeyActions.WINDOW_ATTRIBUTE)){
			return getAttributeAction(action, strings[0]);
		}else if(action.equals(KeyActions.WINDOW_URL)){
			return AutoBase.driver().getCurrentUrl();
		}else if(action.equals("选择性")){
			return elementSelectedStatus();
		}
		return null;
	}
	
	
}
