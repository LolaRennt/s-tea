package org.sky.auto.browser.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;

public class ListElement {
	private IBrowser browser;
	List<Contorl> slist=new ArrayList<Contorl>();
	public ListElement(List<WebElement> list){
		for(int i=0;i<list.size();i++){
			slist.add(new PageContorl(browser,list.get(i)));
		}
	}
	
	private int index=0;
	
	public int getIndex(){
		return index;
	}
	
	public List<Contorl> getSlist() {
		return slist;
	}

	public void setSlist(List<Contorl> slist) {
		this.slist = slist;
	}

	public void select(int index){
		this.index=index;
	}
	
	public Contorl get(int index){
		return slist.get(index);
	}
	/**通过随机数得到元素*/
	public Contorl getByRandom(){
		Random r = new Random();
		int max =getSlist().size();
		int random =r.nextInt(max);
		return get(random);
	}

	public IBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(IBrowser browser) {
		this.browser = browser;
	}
}
