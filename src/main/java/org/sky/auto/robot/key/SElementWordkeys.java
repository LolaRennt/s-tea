package org.sky.auto.robot.key;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.sky.auto.base.AutoBase;

@RobotKeywords
public class SElementWordkeys {
	
	@RobotKeyword
	public void ElementClear(String id){
		AutoBase.sElement(id).clear();
	}
	
	@RobotKeyword
	public void ElementClick(String id){
		AutoBase.sElement(id).click();
	}
	
	@RobotKeyword
	public void ElementDoubleClick(String id){
		AutoBase.sElement(id).doubleClick();
	}
	
	@RobotKeyword
	public void ElementDragAndDrop(String id,String oid){
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
	public int GetComboBoxSize(String id){
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
	
	
}
