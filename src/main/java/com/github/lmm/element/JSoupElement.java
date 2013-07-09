package com.github.lmm.element;

import java.util.Stack;
import org.jsoup.nodes.Element;

public class JSoupElement {
	private Element element;
	private Stack<String> stack;
	private boolean isDisappearId=false;
	public JSoupElement(Element element){
		this.element=element;
		this.stack=new Stack<String>();
	}
	
	private void colloct(){
		this.stack.clear();
		Element node;
		if(hasID(this.element)){
			this.stack.push(this.element.id());
			isDisappearId=true;
		}else{
			node=this.element;
			while(!hasID(node)){
				if(node.elementSiblingIndex()==0){
					this.stack.push(node.tagName());
				}else{
					this.stack.push(node.tagName()+"["+node.elementSiblingIndex()+"]");
				}
				node=node.parent();
			}
			if(hasID(node)){
				this.stack.push(node.id());
				isDisappearId=true;
			}
		}
	}
	
	private boolean hasID(Element e){
		if(e.id()!=null&&!e.id().equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	public String toXpath(){
		String xpath="";
		colloct();
		if(isDisappearId){
			int i=0;
			while(!this.stack.empty()){
				if(i==0){
					xpath=xpath+".//*[@id='"+this.stack.pop()+"']";
				}else{
					xpath=xpath+"/"+this.stack.pop();
				}
				i++;
			}
		}else{
			while(!this.stack.empty()){
				xpath=xpath+"/"+this.stack.pop();
			}
		}
		return xpath;
	}
	
	
//	public static void main(String[] args) throws InterruptedException {
//		WebDriver driver = new FirefoxDriver();
//		driver.get("http://www.hao123.com");
//		String source = driver.getPageSource();
//		Document doc = Jsoup.parse(source);
//		Element e=doc.select("img[src=http://s1.hao123img.com/index/images/search_logo/web.png]").first();
//		JSoupElement je = new JSoupElement(e);
//		System.out.println(je.toXpath());
//		driver.findElement(By.xpath(je.toXpath())).click();
//		Thread.sleep(3000);
//		driver.quit();			
//	}
}
