package org.sky.auto.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sky.auto.base.AutoBase;


public class ListElement {
	List<SElement> slist=new ArrayList<SElement>();
	public ListElement(List<WebElement> list){
		for(int i=0;i<list.size();i++){
			slist.add(new SElement(list.get(i)));
		}
	}
	
	public ListElement(By by){
		this(AutoBase.driver().findElements(by));
	}

	protected List<SElement> getSlist() {
		return slist;
	}

	protected void setSlist(List<SElement> slist) {
		this.slist = slist;
	}
	
	public SElement get(int index){
		return slist.get(index);
	}
	/**通过随机数得到元素*/
	public SElement getByRandom(){
		Random r = new Random();
		int max =getSlist().size();
		int random =r.nextInt(max);
		return get(random);
	}
	
	public int getSize(){
		return slist.size();
	}
	
	
}
