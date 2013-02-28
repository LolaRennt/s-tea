package org.sky.auto.xml;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//import org.openqa.selenium.WebElement;
//import org.sky.auto.element.SElement;
import org.openqa.selenium.WebElement;
import org.sky.auto.base.AutoBase;
import org.sky.auto.element.SElement;
import org.sky.auto.window.Window;

public class XMLParser {
	static Logger logger = Logger.getLogger(XMLParser.class);
	static Document doc;
	static MyVisitor mv=MyVisitor.getInstance();
	public static Document readXML(String file) throws DocumentException{
		SAXReader sr = new SAXReader();
		doc = sr.read(new File(file));
		return doc;
	}
	public Element getRoot(){
		return doc.getRootElement();
	}

	public static void init(String filePath){
		try {
			mv.clear();
			readXML(filePath).accept(mv);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**这个方法必须用在正在查找元素的时候
	 * @param path 表示需要查找的xml文件的目录
	 * @param id 表示在xml里面配置的元素的id值
	 * */
	public static WebElement element(String id,String path){
		WebElement element = null;
		init(path);
		Element e = mv.getElementsById(id);
		Map<String,String> eAttr=mv.getAttribute(e);
		if(e.getParent().isRootElement()){
			Window.selectDefaultWindow();
			if(e.getName().equals("element")){
				if(mv.getElementChilds(e).size()==0){
					logger.info("元素["+id+"]不存在子元素！不需要层级定位！");
					String by= eAttr.get("by");
					int index=0;
					if(eAttr.get("index")!=null){
						try{
							index=Integer.parseInt(eAttr.get("index"));
						}catch(NumberFormatException e1){
							logger.error("元素["+eAttr.get("id")+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
							e1.printStackTrace();
						}
					}
					
					String value=eAttr.get("value");
					try {
						return AutoBase.driver().findElements(mv.locator(by, value)).get(index);
					} catch (Exception e1) {
						logger.error("定位元素["+id+"]的时候,出现错误！");
						e1.printStackTrace();
					}
				}else{
					List<Element> cList=mv.getElementChilds(e);
					//eAttr=mv.getAttribute(e);
					String value=e.attributeValue("value");
					String by=e.attributeValue("by");
					int index=0;
					if(eAttr.get("index")!=null){
						try{
							index=Integer.parseInt(eAttr.get("index"));
						}catch(NumberFormatException e1){
							logger.error("元素["+eAttr.get("id")+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
							e1.printStackTrace();
						}
					}
					try {
						element=AutoBase.driver().findElements(mv.locator(by, value)).get(index);
					} catch (Exception e2) {
						logger.error("定位childElement元素["+value+"]的时候,出现错误！");
						e2.printStackTrace();
					}
					for(int i=0;i<cList.size();i++){
						String cvalue=cList.get(i).attributeValue("value");
						String cby=cList.get(i).attributeValue("by");
						int cindex=0;
						if(cList.get(i).attributeValue("index")!=null){
							try{
								cindex=Integer.parseInt(cList.get(i).attributeValue("index"));
							}catch(NumberFormatException e1){
								logger.error("元素["+cList.get(i).attributeValue("id")+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
								e1.printStackTrace();
							}
						}	
						try {
							element=element.findElements(mv.locator(cby, cvalue)).get(cindex);
						} catch (Exception e1) {
							logger.error("定位childElement元素["+cvalue+"]的时候,出现错误！");
							e1.printStackTrace();
						}	
					}
				}
			}
		}else if(e.getParent().getName().equals("frame")){
			Element pe = e.getParent();
			Map<String,String> peAttr = mv.getAttribute(pe);
			String pby=peAttr.get("by");
			String pid=peAttr.get("id");
			String pvalue=peAttr.get("value");
			int pindex=0;
			if(eAttr.get("index")!=null){
				try{
					pindex=Integer.parseInt(eAttr.get("index"));
				}catch(NumberFormatException e1){
					logger.error("元素["+eAttr.get("id")+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					e1.printStackTrace();
				}
			}
			try {
				Window.selectFrame(AutoBase.driver().findElements(mv.locator(pby,pvalue)).get(pindex));
			} catch (Exception e1) {
				logger.error("定位元素["+pid+"]的时候,出现错误！");
				e1.printStackTrace();
			}
			if(e.getName().equals("element")){
				if(mv.getElementChilds(e).size()==0){
					String by= eAttr.get("by");
					int index=0;
					if(eAttr.get("index")!=null){
						try{
							index=Integer.parseInt(eAttr.get("index"));
						}catch(NumberFormatException e1){
							logger.error("元素["+eAttr.get("id")+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
							e1.printStackTrace();
						}
					}
					String value=eAttr.get("value");
					try {
						return AutoBase.driver().findElements(mv.locator(by, value)).get(index);
					} catch (Exception e1) {
						logger.error("定位childElement元素["+value+"]的时候,出现错误！");
						e1.printStackTrace();
					}
				}else{
					List<Element> cList=mv.getElementChilds(e);
					//eAttr=mv.getAttribute(e);
					String value=e.attributeValue("value");
					String by=e.attributeValue("by");
					int index=0;
					if(eAttr.get("index")!=null){
						try{
							index=Integer.parseInt(eAttr.get("index"));
						}catch(NumberFormatException e1){
							logger.error("元素["+eAttr.get("id")+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
							e1.printStackTrace();
						}
					}
					try {
						element=AutoBase.driver().findElements(mv.locator(by, value)).get(index);
					} catch (Exception e2) {
						logger.error("定位childElement元素["+value+"]的时候,出现错误！");
						e2.printStackTrace();
					}
					for(int i=0;i<cList.size();i++){
						String cvalue=cList.get(i).attributeValue("value");
						String cby=cList.get(i).attributeValue("by");
						int cindex=0;
						if(cList.get(i).attributeValue("index")!=null){
							try{
								cindex=Integer.parseInt(cList.get(i).attributeValue("index"));
							}catch(NumberFormatException e1){
								logger.error("元素["+cList.get(i).attributeValue("id")+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
								e1.printStackTrace();
							}
						}	
						try {
							element=element.findElements(mv.locator(cby, cvalue)).get(cindex);
						} catch (Exception e1) {
							logger.error("定位childElement元素["+cvalue+"]的时候,出现错误！");
							e1.printStackTrace();
						}	
					}
				}			
			}
		}
		return element;
	}
	
	public static SElement sElement(String id,String path){
		return new SElement(element(id,path));
		
	}
	
	/**处理批量元素，可以返回一个list*/
	public static List<WebElement> elements(String id,String path){
		List<WebElement> elist = null;
		List<Element> list;
		init(path);
		Element e = mv.getElementsById(id);
		if(e.getParent().isRootElement()){
			String value = mv.getAttribute(e).get("value");
			String by = mv.getAttribute(e).get("by");
			list=mv.getElementChilds(e);
			if(e.getName().equals("frame")){
				Element ce=list.get(0);
				if(mv.getElementChilds(ce).size()>0){
					int index =0;
					if(mv.getAttribute(e).get("index")!=null){
						try{
							index=Integer.parseInt(mv.getAttribute(ce).get("index"));
						}catch(NumberFormatException e1){
							logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
							e1.printStackTrace();
						}
					}
					try{
						Window.selectFrame(AutoBase.driver().findElements(mv.locator(by,value)).get(index));
					} catch (Exception e1) {
						logger.error("定位元素["+id+"]的时候,出现错误！");
						e1.printStackTrace();
					}
					int clen=mv.getElementChilds(ce).size();
					if(clen>1){
						WebElement element=null;
						for(int i=0;i<clen;i++){
							String cid=mv.getElementChilds(ce).get(i).attributeValue("id");
							String cby=mv.getElementChilds(ce).get(i).attributeValue("by");
							String cvalue=mv.getElementChilds(ce).get(i).attributeValue("value");			
							if(i==0){
								try {
									element=AutoBase.driver().findElement(mv.locator(cby, cvalue));
								} catch (Exception e1) {
									logger.error("定位元素["+cid+"]的时候,出现错误！");
									e1.printStackTrace();
								}
							}else if(i==(clen-1)){
								try {
									elist=element.findElements(mv.locator(cby, cvalue));
								} catch (Exception e1) {
									logger.error("定位元素["+cid+"]的时候,出现错误！");
									e1.printStackTrace();
								}
							}else{
								try {
									element=element.findElement(mv.locator(cby, cvalue));
								} catch (Exception e1) {
									logger.error("定位元素["+cid+"]的时候,出现错误！");
									e1.printStackTrace();
								}
							}
						}
					}else if(clen==1){
						String cid=mv.getElementChilds(ce).get(0).attributeValue("id");
						String cby=mv.getElementChilds(ce).get(0).attributeValue("by");
						String cvalue=mv.getElementChilds(ce).get(0).attributeValue("value");	
						try {
							elist=AutoBase.driver().findElements(mv.locator(cby, cvalue));
						} catch (Exception e1) {
							logger.error("定位元素["+cid+"]的时候,出现错误！");
							e1.printStackTrace();
						}
					}
				}else if(mv.getAttribute(ce).size()==0){
					try {
						elist=AutoBase.driver().findElements(mv.locator(by, value));
					} catch (Exception e1) {
						logger.error("定位元素["+id+"]的时候,出现错误！");
						e1.printStackTrace();
					}
				}
			}else {
				int clen=mv.getElementChilds(e).size();
				if(clen>1){
					WebElement element=null;
					for(int i=0;i<clen;i++){
						String cid=mv.getElementChilds(e).get(i).attributeValue("id");
						String cby=mv.getElementChilds(e).get(i).attributeValue("by");
						String cvalue=mv.getElementChilds(e).get(i).attributeValue("value");			
						if(i==0){
							try {
								element=AutoBase.driver().findElement(mv.locator(cby, cvalue));
							} catch (Exception e1) {
								logger.error("定位元素["+cid+"]的时候,出现错误！");
								e1.printStackTrace();
							}
						}else if(i==(clen-1)){
							try {
								elist=element.findElements(mv.locator(cby, cvalue));
							} catch (Exception e1) {
								logger.error("定位元素["+cid+"]的时候,出现错误！");
								e1.printStackTrace();
							}
						}else{
							try {
								element=element.findElement(mv.locator(cby, cvalue));
							} catch (Exception e1) {
								logger.error("定位元素["+cid+"]的时候,出现错误！");
								e1.printStackTrace();
							}
						}
					}
				}else if(clen==1){
					String cid=mv.getElementChilds(e).get(0).attributeValue("id");
					String cby=mv.getElementChilds(e).get(0).attributeValue("by");
					String cvalue=mv.getElementChilds(e).get(0).attributeValue("value");	
					try {
						elist=AutoBase.driver().findElements(mv.locator(cby, cvalue));
					} catch (Exception e1) {
						logger.error("定位元素["+cid+"]的时候,出现错误！");
						e1.printStackTrace();
					}
				}else if(mv.getAttribute(e).size()==0){
				String cid=e.attributeValue("id");
				String cby=e.attributeValue("by");
				String cvalue=e.attributeValue("value");
				try {
					elist=AutoBase.driver().findElements(mv.locator(cby, cvalue));
				} catch (Exception e1) {
					logger.error("定位元素["+cid+"]的时候,出现错误！");
					e1.printStackTrace();
				}
			}
			}
		}
		return elist;
	}
	
	
//	/**返回一组元素的数组，返回类型是SElement
//	 * 这个方法暂时作为保留方法，暂时不做实现，想实现list的方式请使用elements()的方法。
//	 * */
//	public List<SElement> sElements(){
//		return null;
//		
//	}
	

	
}
