package org.sky.auto.xml;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.sky.auto.base.AutoBase;
import org.sky.auto.element.SElement;
import org.sky.auto.exception.MyAutoException;
import org.sky.auto.exception.MyElementNotFoundException;
import org.sky.auto.window.Window;

public class XmlProvider{
	static Logger logger = Logger.getLogger(XmlProvider.class);

	private String path;
	public  WebElement element(String id ,String path){
		XMLDocument xd = new XMLDocument(path);
		XMLElements xe = new XMLElements(xd);
		//XMLNode xn = xe.getXMLNode(id);
		XMLElement xn = xe.getXMLElementOfAll(id);
		return elementById(xn, id);
	}
	
	public synchronized List<WebElement> elements(String id,String path){
		XMLDocument xd = new XMLDocument(path);
		XMLElements xe = new XMLElements(xd);
		XMLElement xn =xe.getXMLElementOfAll(id);
		return elementsById(xn, id);
		
	}
	
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
	
	public WebElement elementById(XMLElement xn,String id){
		Window.selectDefaultWindow();
		WebElement we=null;
		XmlToJavaTools xtj = new XmlToJavaTools();
		if(xn==null){
			try {
				throw new MyElementNotFoundException("查找元素["+id+"]的时候出现错误！没有找到正确的元素！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(xn.getElement().getParent().isRootElement()){
			int index =0;
			if(xn.getIndex()!=null){
				index = Integer.parseInt(xn.getIndex());
				try{
					index=Integer.parseInt(xn.getIndex());
				}catch(NumberFormatException e1){
					logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
				}
			}
			try {
				we=AutoBase.driver().findElements(xtj.locator(xn.getBy(), xn.getValue())).get(index);
			} catch (Exception e) {
				logger.error("定位元素["+id+"]的时候,出现错误！");
				throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
			}	
			if(((XMLElement) xn).getXMLChildElements().size()!=0){
				for(XMLChildElement xc : ((XMLElement)xn).getXMLChildElements()){
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
		}else{
			XMLFrame xf = new XMLFrame();
			xf.setElement(xn.getElement().getParent());
			int pindex=0;
			if(xf.getIndex()!=null){
				try{
					pindex=Integer.parseInt(xf.getIndex());
				}catch(NumberFormatException e){
					logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
				}
			}
			try{
				Window.selectFrame(AutoBase.driver().findElements(xtj.locator(xf.getBy(), xf.getValue())).get(pindex));
			}catch(Exception e){
				logger.error("定位元素["+id+"]的时候,出现错误！进行切换frame的时候定位出错！");
				throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！进行切换frame的时候定位出错！");
			}
			int index=0;
			if(xf.getIndex()!=null){
				try{
					index=Integer.parseInt(xf.getIndex());
				}catch(NumberFormatException e){
					logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
				}
			}
			try{
				we=AutoBase.driver().findElements(xtj.locator(xn.getBy(), xn.getValue())).get(index);
			}catch(Exception e){
				logger.error("定位元素["+id+"]的时候,出现错误！");
				throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
			}
			if(xn.getXMLChildElements().size()!=0){
				for(XMLChildElement xce:xn.getXMLChildElements()){
					int cindex=0;
					if(xce.getIndex()!=null){
						try{
							cindex=Integer.parseInt(xce.getIndex());
						}catch(NumberFormatException e){
							logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
							throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
						}
					}
					try{
						we=we.findElements(xtj.locator(xce.getBy(), xce.getValue())).get(cindex);
						//Window.selectDefaultWindow();
					}catch(Exception e){
						logger.error("定位元素["+id+"]的child元素"+xce.getValue()+"的时候,出现错误！");
						throw new MyElementNotFoundException("定位元素["+id+"]的child元素"+xce.getValue()+"的时候,出现错误！");
					}
				}
			}
	}
	return we;
	}
	
	public List<WebElement> elementsById(XMLElement xn,String id){
		WebElement we=null;
		XmlToJavaTools xtj = new XmlToJavaTools();
		if(xn.getElement().getParent().getName().equals("list")){
			Window.selectDefaultWindow();
			if(xn.getXMLChildElements().size()==0){
				try {
					return AutoBase.driver().findElements(xtj.locator(xn.getBy(), xn.getValue()));
				} catch (Exception e) {
					logger.error("定位元素["+id+"]的时候,出现错误！");
					throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
				}
			}else{
				int index = 0;
				if(xn.getIndex()!=null){
					try{
						index=Integer.parseInt(xn.getIndex());
					}catch(NumberFormatException e){
						logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
						throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					}
				}
				try {
					we=AutoBase.driver().findElements(xtj.locator(xn.getBy(), xn.getValue())).get(index);
				} catch (Exception e) {
					logger.error("定位元素["+id+"]的时候,出现错误！");
					throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
				}
				for(int i=0;i<xn.getXMLChildElements().size();i++){
					if(i==xn.getXMLChildElements().size()-1){
						XMLChildElement xce = xn.getXMLChildElements().get(i);
						try {
							return we.findElements(xtj.locator(xce.getBy(), xce.getValue()));
						} catch (Exception e) {
							logger.error("定位元素["+id+"]的时候,出现错误！");
							throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
						}
					}else{
						int cindex =0;
						XMLChildElement xce = xn.getXMLChildElements().get(i);
						if(xce.getIndex()!=null){
							try{
								index=Integer.parseInt(xn.getIndex());
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
		} else if(xn.getElement().getParent().getName().equals("frame")){
			Window.selectDefaultWindow();
			int pindex = 0;
			XMLFrame xf = new XMLFrame();
			xf.setElement(xn.getElement().getParent());
			if(xf.getIndex()!=null){
				try{
					pindex=Integer.parseInt(xn.getIndex());
				}catch(NumberFormatException e){
					logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
				}
			}
			try{
				Window.selectFrame(AutoBase.driver().findElements(xtj.locator(xf.getBy(), xf.getValue())).get(pindex));
			}catch(Exception e){
				logger.error("定位元素["+id+"]的时候,出现错误！进行切换frame的时候定位出错！");
				throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！进行切换frame的时候定位出错！");
			}
			if(xn.getXMLChildElements().size()==0){
				try {
					return AutoBase.driver().findElements(xtj.locator(xn.getBy(), xn.getValue()));
				} catch (Exception e) {
					logger.error("定位元素["+id+"]的时候,出现错误！");
					throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
				}
			}else{
				int index = 0;
				if(xn.getIndex()!=null){
					try{
						index=Integer.parseInt(xn.getIndex());
					}catch(NumberFormatException e){
						logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
						throw new MyAutoException("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					}
				}
				try {
					we=AutoBase.driver().findElements(xtj.locator(xn.getBy(), xn.getValue())).get(index);
				} catch (Exception e) {
					logger.error("定位元素["+id+"]的时候,出现错误！");
					throw new MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
				}
				for(int i=0;i<xn.getXMLChildElements().size();i++){
					if(i==xn.getXMLChildElements().size()-1){
						XMLChildElement xce = xn.getXMLChildElements().get(i);
						try {
							return we.findElements(xtj.locator(xce.getBy(), xce.getValue()));
						} catch (Exception e) {
							logger.error("定位元素["+id+"]的时候,出现错误！");
							throw new  MyElementNotFoundException("定位元素["+id+"]的时候,出现错误！");
						}
					}else{
						int cindex =0;
						XMLChildElement xce = xn.getXMLChildElements().get(i);
						if(xce.getIndex()!=null){
							try{
								index=Integer.parseInt(xn.getIndex());
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
	
	public WebElement element(String id){
		XMLLoader.load("xml");
		return elementById(XMLLoader.getXMLElement(id),id);
	}
	
	public List<WebElement> elements(String id){
		XMLLoader.load("xml");
		return elementsById(XMLLoader.getXMLElement(id),id);
	}
	
	
}
