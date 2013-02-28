package org.sky.auto.story.action;

import org.sky.auto.element.CheckBox;

public class CheckboxActions extends Actions{
	private CheckBox cb;
	public CheckboxActions(CheckBox cb){
		this.setCheckBox(cb);
	}
	public CheckBox getCheckBox() {
		return cb;
	}
	public void setCheckBox(CheckBox cb) {
		this.cb = cb;
	}
	
	public void checkboxAction(String action){
		if(action.equals("选中")){
			cb.setStatus(true);
		}else if(action.equals("取消选中")){
			cb.setStatus(false);
		}
	}
}
