package org.sky.auto.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;

public class ListElement {
	List<SElement> slist=new ArrayList<SElement>();
	public ListElement(List<WebElement> list){
		for(int i=0;i<list.size();i++){
			slist.add(new SElement(list.get(i)));
		}
	}
	
	private int index=0;
	
	public int getIndex(){
		return index;
	}
	
	public List<SElement> getSlist() {
		return slist;
	}

	public void setSlist(List<SElement> slist) {
		this.slist = slist;
	}

	public void select(int index){
		this.index=index;
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
}
