package org.sky.auto.xml;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
//import java.util.Stack;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sky.auto.base.AutoBase;
import org.sky.auto.element.SElement;
import org.sky.auto.exception.MyAutoException;
import org.sky.auto.exception.MyElementNotFoundException;
import org.sky.auto.window.Window;

public class XMLToWebElement{
	static Logger logger = Logger.getLogger(XMLToWebElement.class);
	//private AutoDriver driver;
	private String path;
		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		
		public SElement sElement(String id,String path){
			SElement se= new SElement(element(id,path));
			se.setId(id);
			return se;
		}
		
		public List<SElement> sElements(String id,String path){
			List<SElement> slist =new ArrayList<SElement>();
			for(WebElement we:elements(id,path)){
				SElement se=new SElement(we);
				se.setId(id);
				slist.add(se);
			}
			return slist;
		}
		
		public WebElement element(String id){
			XMLLoader.load("xml");
			XMLElement xe = XMLLoader.getXMLElement(id);
			if(xe.isFrameElement()){
				Window.selectDefaultWindow();
				logger.info("元素"+id+"是Frame内的元素！");
				enterFrame(xe, id);
				return getLocator(xe, id);
			}else{
				Window.selectDefaultWindow();
				return getLocator(xe, id);
			}
		}
		
		public List<WebElement> elements(String id){
			return elements(id,"xml");
		}
		
		
	public void enterFrame(XMLElement xe,String id){
		Stack<Map<By,Integer>> stack = new Stack<Map<By,Integer>>();
		int j=getParentFrameLevel(xe);
		XmlToJavaTools xjt = new XmlToJavaTools();
		Element e=xe.getElement().getParent();
		for(int i=0;i<j;i++){
			Map<By,Integer> frameList = new HashMap<By,Integer>();
			int index=0;
			String value = e.attributeValue("value").trim();
			String by = e.attributeValue("by").trim();
			if(e.getParent().attributeValue("index")!=null){
				index = Integer.parseInt(e.getParent().attributeValue("index").trim());
			}
			//System.out.println(index);
			frameList.put(xjt.locator(by, value), index);
			e=e.getParent();
			stack.push(frameList);
		}
		//System.out.println(stack.size());
		while(!stack.empty()){
			//WebElement we = null;
			By key=null;
			Integer value=null;
			Iterator<Entry<By,Integer>> iter = stack.pop().entrySet().iterator();
			while(iter.hasNext()){
				Entry<By,Integer> entry = iter.next();
				key=entry.getKey();
				//System.out.println(key);
				value=entry.getValue();
				//System.out.println(value);
			}
				try{
					Window.selectFrame(AutoBase.driver().findElements(key).get(value));
				}catch(Exception e1){
					throw new MyElementNotFoundException("切入进Frame的时候出现错误！请检查Frame的定位是否正确。错误的定位的方式-->"+key);
				}
		}
	}
	
	public WebElement element(String id,String path){
		XMLDocument xd = new XMLDocument(path);
		XMLElements xes = new XMLElements(xd);
		//XMLNode xn = xe.getXMLNode(id);
		XMLElement xe = xes.getXMLElementOfAll(id);
		if(xe.isFrameElement()){
			Window.selectDefaultWindow();
			enterFrame(xe, id);
			return getLocator(xe, id);
		}else{
			Window.selectDefaultWindow();
			return getLocator(xe, id);
		}
	}
	
	public List<WebElement>elements(String id,String path){
		XMLDocument xd = new XMLDocument(path);
		XMLElements xes = new XMLElements(xd);
		XMLList xl = xes.getXMLList(id);
		if(xl.getXMLElement()!=null){
			Window.selectDefaultWindow();
			return getListLocator(xl, id);
		}else if(xl.getFrameXMLElement()!=null){
			Window.selectDefaultWindow();
			enterListFrame(xl.getFrameXMLElement(), id);
			return getListLocator(xl, id);
		}
		return null;
	}
	
	protected WebElement getLocator(XMLElement xe,String id){
		XmlToJavaTools xtj = new XmlToJavaTools();
		WebElement we=null;
		if(xe==null){
			try {
				throw new MyElementNotFoundException("查找元素["+id+"]的时候出现错误！没有找到正确的元素！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(xe.isListElement()){
			throw new MyElementNotFoundException("查找元素["+id+"]的时候出现了错误，id为List元素，不能够返回单个元素值！");
		}
		int index =0;
		if(xe.getIndex()!=null){
			index = Integer.parseInt(xe.getIndex());
			try{
				index=Integer.parseInt(xe.getIndex());
			}catch(NumberFormatException e1){
				logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
				throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
			}
		}
		try {
			we=AutoBase.driver().findElements(xtj.locator(xe.getBy(), xe.getValue())).get(index);
		} catch (Exception e) {
			logger.error("定位元素["+id+"]的时候,出现错误！");
			throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
		}	
		List<XMLChildElement> xcelements=xe.getXMLChildElements();
		if(xcelements.size()!=0){
			for(XMLChildElement xc : xcelements){
				int cindex=0;
				if(xc.getIndex()!=null){
					try{
						cindex=Integer.parseInt(xc.getIndex());
					}catch(NumberFormatException e1){
						logger.error("child元素["+xc.getValue()+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
						throw new MyAutoException("child元素["+xc.getValue()+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					}
				}
				try{
					we=we.findElements(xtj.locator(xc.getBy(), xc.getValue())).get(cindex);
				}catch(Exception e){
					logger.error("定位元素["+id+"]的child元素的时候,出现错误！");
					throw new MyElementNotFoundException("定位元素["+id+"]的child元素的时候,出现错误！");
				}
			}
		}
		return we;
	}
	
	protected List<WebElement> getListLocator(XMLList xl,String id){
		//Window.selectDefaultWindow();
		XmlToJavaTools xtj = new XmlToJavaTools();
		WebElement we=null;
		if(xl==null){
			try {
				throw new MyElementNotFoundException("查找元素["+id+"]的时候出现错误！没有找到正确的元素！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(xl.getElement()!=null){
			XMLElement xe = xl.getXMLElement();
			int index =0;
			if(xe.getIndex()!=null){
				index = Integer.parseInt(xe.getIndex());
				try{
					index=Integer.parseInt(xe.getIndex());
				}catch(NumberFormatException e1){
					logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
				}
			}
			try {
				we=AutoBase.driver().findElements(xtj.locator(xe.getBy(), xe.getValue())).get(index);
			} catch (Exception e) {
				logger.error("定位元素["+id+"]的时候,出现错误！");
				throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
			}	
			List<XMLChildElement> xcelements=xe.getXMLChildElements();
			if(xcelements.size()!=0){
				for(int i=0;i<xcelements.size();i++){
					if(i==xcelements.size()-1){
						XMLChildElement xce = xcelements.get(i);
						try {
							return we.findElements(xtj.locator(xce.getBy(), xce.getValue()));
						} catch (Exception e) {
							logger.error("定位元素["+id+"]的时候,出现错误！");
							throw new  MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
						}
					}else{
						int cindex =0;
						XMLChildElement xce = xcelements.get(i);
						if(xce.getIndex()!=null){
							try{
								index=Integer.parseInt(xce.getIndex());
							}catch(NumberFormatException e){
								logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
								throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
							}
						}
						try{
							we=we.findElements(xtj.locator(xce.getBy(), xce.getValue())).get(cindex);
						}catch(Exception e){
							logger.error("定位元素["+id+"]的时候,出现错误！");
							throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
						}
					}
				}
			}
		}	
		return null;
	}
	
		
	public void enterListFrame(XMLElement xe,String id){
		Stack<Map<By,Integer>> stack = new Stack<Map<By,Integer>>();
		int j=getListParentFrameLevel(xe);
		XmlToJavaTools xjt = new XmlToJavaTools();
		Element e=xe.getElement().getParent();
		for(int i=0;i<j;i++){
			Map<By,Integer> frameList = new HashMap<By,Integer>();
			int index=0;
			String value = e.attributeValue("value").trim();
			String by = e.attributeValue("by").trim();
			if(e.getParent().attributeValue("index")!=null){
				index = Integer.parseInt(e.getParent().attributeValue("index").trim());
			}
			//System.out.println(index);
			frameList.put(xjt.locator(by, value), index);
			e=e.getParent();
			stack.push(frameList);
		}
		//System.out.println(stack.size());
		while(!stack.empty()){
			//WebElement we = null;
			By key=null;
			Integer value=null;
			Iterator<Entry<By,Integer>> iter = stack.pop().entrySet().iterator();
			while(iter.hasNext()){
				Entry<By,Integer> entry = iter.next();
				key=entry.getKey();
				//System.out.println(key);
				value=entry.getValue();
				//System.out.println(value);
			}
				try{
					Window.selectFrame(AutoBase.driver().findElements(key).get(value));
				}catch(Exception e1){
					throw new MyElementNotFoundException("切入进Frame的时候出现错误！请检查Frame的定位是否正确。错误的定位的方式-->"+key);
				}
		}
	}
	
	
	
	
	private int getParentFrameLevel(XMLElement xe){
		int i=0;
		Element e = xe.getElement().getParent();
		while(!e.isRootElement()){
			i++;
			e=e.getParent();
		}
		//System.out.println("元素含有的Frame数目为：->"+i);
		return i;
	}
	
	private int getListParentFrameLevel(XMLElement xe){
		Element e = xe.getElement().getParent();
		int i=0;
		while(!e.getName().toLowerCase().equals("list")){
			i++;
			e=e.getParent();
		}
		return i;
	}
}

