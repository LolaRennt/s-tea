package org.sky.auto.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.openqa.selenium.By;

public class MyVisitor extends VisitorSupport{
	static Logger logger = Logger.getLogger(MyVisitor.class);
	static MyVisitor mv;
	Set<Element> eSet = new HashSet<Element>();
	Set<Element> fSet = new HashSet<Element>();
	//Set<Element> ecSet = new HashSet<Element>();
	Set<Element> allSet = new HashSet<Element>();
	@Override
	public void visit(Element node){
		if(node.getName().equals("element")){
			eSet.add(node);
		}else if(node.getName().equals("frame")){
			fSet.add(node);
		}
		allSet.add(node);
//		System.out.println("得到element元素的个数为-->"+eSet.size());
//		System.out.println("得到的frame元素的个数为-->"+fSet.size());
////		System.out.println("得到的所有元素的个数为-->"+allSet.size());
//		for(Element s:allSet){
//			System.out.println(s.getName()+s.attributeValue("id"));
//		}
	}
	
	public By locator(String by,String selector) throws Exception{	
		By b=null;
		if(by.equals("id")){
			b = By.id(selector);
		}else if(by.equals("css")){
			b = By.cssSelector(selector);
		}else if(by.equals("tagName")){
			b = By.tagName(selector);
		}else if(by.equals("class")){
			b=By.className(selector);
		}else if(by.equals("xpath")){
			b=By.xpath(selector);
		}else if(by.equals("linkText")){
			b=By.linkText(selector);
		}else if(by.equals("name")){
			b=By.name(selector);
		}else if(by.equals("partialLinkText")){
			b=By.partialLinkText(selector);
		}else{
			throw new Exception("["+by+"]这种定位方式没有被找到！");
		}
		return b;
	}
	/**通过元素来获得By和index的map*/
	public Map<By,Integer> by(Element e) throws Exception{	
		Map<By,Integer> byMap= new HashMap<By,Integer>();
		Map<String,String> aMap=getAttribute(e);
		String by=aMap.get("by");
		String value =aMap.get("value");
		int index=0;
		try{
			index=Integer.parseInt(aMap.get("index"));
		}catch(NumberFormatException e1){
			logger.error("元素的index值不能够被转化成为int类型，index的属性值输入错误！");
			e1.printStackTrace();
		}
		By b=null;
		if(by.equals("id")){
			b = By.id(value);
			byMap.put(b, index);
		}else if(by.equals("css")){
			b = By.cssSelector(value);
			byMap.put(b, index);
		}else if(by.equals("tagName")){
			b = By.tagName(value);
			byMap.put(b, index);
		}else if(by.equals("class")){
			b=By.className(value);
			byMap.put(b, index);
		}else if(by.equals("xpath")){
			b=By.xpath(value);
			byMap.put(b, index);
		}else if(by.equals("linkText")){
			b=By.linkText(value);
			byMap.put(b, index);
		}else if(by.equals("name")){
			b=By.name(value);
			byMap.put(b, index);
		}else if(by.equals("partialLinkText")){
			b=By.partialLinkText(value);
			byMap.put(b, index);
		}else{
			logger.error("["+by+"]这种定位方式没有被找到！");
			throw new Exception("["+by+"]这种定位方式没有被找到！");
		}
		return byMap;
	}
	
	public Map<By,Integer> by(String by,String selector,int index) throws Exception{
		Map<By,Integer> byMap= new HashMap<By,Integer>();
		By b=null;
		if(by.equals("id")){
			b = By.id(selector);
			byMap.put(b, index);
		}else if(by.equals("css")){
			b = By.cssSelector(selector);
			byMap.put(b, index);
		}else if(by.equals("tagName")){
			b = By.tagName(selector);
			byMap.put(b, index);
		}else if(by.equals("class")){
			b=By.className(selector);
			byMap.put(b, index);
		}else if(by.equals("xpath")){
			b=By.xpath(selector);
			byMap.put(b, index);
		}else if(by.equals("linkText")){
			b=By.linkText(selector);
			byMap.put(b, index);
		}else if(by.equals("name")){
			b=By.name(selector);
			byMap.put(b, index);
		}else if(by.equals("partialLinkText")){
			b=By.partialLinkText(selector);
			byMap.put(b, index);
		}else{
			logger.error(by+"这种定位方式没有被找到！");
			throw new Exception("["+by+"]这种定位方式没有被找到！");
		}
		return byMap;
	}
	
	/**这个方法是用在指定的frame元素瞎的element元素的list*/
	public List<Element> getFrameElements(Element frame){
		//Map<String,String> elementMap=new HashMap<String,String>();
		List<Element> eList = new ArrayList<Element>();
		@SuppressWarnings("rawtypes")
		Iterator iter;
		for(iter=frame.elementIterator("element");iter.hasNext();){
			Element element = (Element)iter.next();
			eList.add(element);
		}
		return eList;
	}
	
	/**这个方法是依次查找element下的child元素*/
	@SuppressWarnings("rawtypes")
	public List<Element> getElementChilds(Element element){
		List<Element> cList = new ArrayList<Element>();
		Iterator iter;
		for(iter=element.elementIterator("childElement");iter.hasNext();){
			Element e = (Element)iter.next();
			cList.add(e);
			//logger.info("元素childElement["+e.attributeValue("value")+"]存储成功！");
		}
		return cList;
	}
	
	/**这个方法可能获得Element元素下面所有的childElement元素的list，里面存有相关的属性*/
	public List<Map<By,Integer>> getElementChildsList(Element element){
		List<Map<By,Integer>> cList = new ArrayList<Map<By,Integer>>();
		@SuppressWarnings("rawtypes")
		Iterator iter;
		for(iter=element.elementIterator("childElement");iter.hasNext();){
			Element e = (Element)iter.next();
			String by=e.attributeValue("by");
			String value=e.attributeValue("value");
				try{
					int index =Integer.parseInt(e.attributeValue("index"));
					try {
						cList.add(by(by,value,index));
					} catch (Exception e1) {
						logger.error("定位childElement元素["+value+"]的时候,出现错误！");
						e1.printStackTrace();
					}
					//logger.info("元素childElement["+value+"]存储成功！");
				}catch(NumberFormatException e1){
					logger.error("元素的index值不能够被转化成为int类型，index的属性值输入错误！");
					e1.printStackTrace();
				}
		}
		return cList;
	}
	
	/**得到所有有id属性值的元素。*/
	public Map<String,Element> getIdElements(){
		Map<String,Element> eMap=new HashMap<String,Element>();
		for(Element e: allSet){
			String idAttr=e.attributeValue("id");
			if(idAttr!=null){
				eMap.put(idAttr, e);
			}
		}
		//System.out.println("得到id属性的元素长度为-->"+eMap.size());
		return eMap;
	}
	/**通过id值得到这个Element类型的元素*/
	public Element getElementsById(String id){
		return getIdElements().get(id);
	}
	/**得到这个元素内的所有属性集合,以Map的形式来进行存储*/
	public Map<String,String> getAttribute(Element e){
		Map<String,String> aMap=new HashMap<String,String>();
		Iterator<?> iter = e.attributeIterator();
		while(iter.hasNext()){
			Attribute attr=(Attribute) iter.next();
			aMap.put(attr.getName(),attr.getValue());
		}
		return aMap;
	}
	
	public static MyVisitor getInstance(){
		if(mv==null){
			return new MyVisitor();
		}
		return mv;
	}
	
	public void clear(){
		eSet.clear();
		fSet.clear();
		allSet.clear();
	}
	
	
}
