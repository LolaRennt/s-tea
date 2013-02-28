package org.sky.auto.story.action;

import org.apache.log4j.Logger;
import org.sky.auto.element.ComoboBox;

public class SelectActions extends Actions{
	private static Logger logger = Logger.getLogger(SelectActions.class);
	private ComoboBox cb;
	public SelectActions(ComoboBox cb){
		this.cb=cb;
	}
		
	public void selectAction(String action,String...obj){
		if(action.equals("通过索引值查找->")){
			cb.selectByIndex(Integer.parseInt(obj[0]));
		}else if(action.equals("通过随机的索引值查找")){
			cb.selectByRandomIndex();
		}else if(action.equals("通过value值查找->")){
			cb.selectByValue(obj[0]);
		}else if(action.equals("通过显示的内容查找->")){
			cb.selectByVisiableText(obj[0]);
		}else{
			logger.error("没有找到关于此Select控件的相关关键字,未知的操作！");
		}
	}
	
	public ComoboBox getComoboBox() {
		return cb;
	}
	public void setComoboBox(ComoboBox cb) {
		this.cb = cb;
	}
	
	
	
	
	
	
}
