package org.sky.auto.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;
import org.sky.auto.element.SElement;
import org.sky.auto.window.Window;

public class XMLBrowserProvider {
	private IBrowser browser;
	static Logger logger = Logger.getLogger(XmlProvider.class);
	
	public XMLBrowserProvider(IBrowser browser){
		this.browser=browser;
	}

	private String path;
	public  WebElement element(String id ,String path){
		WebElement we=null;
		XmlToJavaTools xtj = new XmlToJavaTools();
		XMLDocument xd = new XMLDocument(path);
		XMLElements xe = new XMLElements(xd);
		//XMLNode xn = xe.getXMLNode(id);
		XMLElement xn = xe.getXMLElementOfAll(id);
			if(xn==null){
				try {
					throw new Exception("查找元素的时候出现错误！没有找到正确的元素！");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(xn.getElement().getParent().isRootElement()){
				//Window.selectDefaultWindow();
				browser.selectDefaultWindow();
				int index =0;
				if(xn.getIndex()!=null){
					index = Integer.parseInt(xn.getIndex());
					try{
						index=Integer.parseInt(xn.getId());
					}catch(NumberFormatException e1){
						logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
						e1.printStackTrace();
					}
				}
				try {
					we=browser.browser().findElements(xtj.locator(xn.getBy(), xn.getValue())).get(index);
				} catch (Exception e) {
					logger.error("定位元素["+id+"]的时候,出现错误！");
					e.printStackTrace();
				}	
				if(((XMLElement) xn).getXMLChildElements().size()!=0){
					for(XMLChildElement xc : ((XMLElement)xn).getXMLChildElements()){
						int cindex=0;
						if(xc.getIndex()!=null){
							try{
								cindex=Integer.parseInt(xc.getIndex());
							}catch(NumberFormatException e1){
								logger.error("child元素["+xc.getValue()+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
								e1.printStackTrace();
							}
						}
						try{
							we=we.findElements(xtj.locator(xc.getBy(), xc.getValue())).get(cindex);
						}catch(Exception e){
							logger.error("定位元素["+id+"]的child元素的时候,出现错误！");
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
						e.printStackTrace();
					}
				}
				try{
					//Window.selectFrame(AutoBase.driver().findElements(xtj.locator(xf.getBy(), xf.getValue())).get(pindex));
					browser.frame(browser.browser().findElements(xtj.locator(xf.getBy(), xf.getValue())).get(pindex));
				}catch(Exception e){
					logger.error("定位元素["+id+"]的时候,出现错误！进行切换frame的时候定位出错！");
					e.printStackTrace();
				}
				int index=0;
				if(xf.getIndex()!=null){
					try{
						index=Integer.parseInt(xf.getIndex());
					}catch(NumberFormatException e){
						logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
						e.printStackTrace();
					}
				}
				try{
					we=browser.browser().findElements(xtj.locator(xn.getBy(), xn.getValue())).get(index);
				}catch(Exception e){
					logger.error("定位元素["+id+"]的时候,出现错误！");
					e.printStackTrace();
				}
				if(xn.getXMLChildElements().size()!=0){
					for(XMLChildElement xce:xn.getXMLChildElements()){
						int cindex=0;
						if(xce.getIndex()!=null){
							try{
								cindex=Integer.parseInt(xf.getIndex());
							}catch(NumberFormatException e){
								logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
								e.printStackTrace();
							}
						}
						try{
							we=we.findElements(xtj.locator(xce.getBy(), xce.getValue())).get(cindex);
						}catch(Exception e){
							logger.error("定位元素["+id+"]的child元素"+xce.getValue()+"的时候,出现错误！");
							e.printStackTrace();
						}
					}
				}
		}
		return we;
	}
	
	public synchronized List<WebElement> elements(String id,String path){
		WebElement we=null;
		XmlToJavaTools xtj = new XmlToJavaTools();
		XMLDocument xd = new XMLDocument(path);
		XMLElements xe = new XMLElements(xd);
		XMLElement xn =xe.getXMLElementOfAll(id);
		if(xn.getElement().getParent().getName().equals("list")){
			Window.selectDefaultWindow();
			if(xn.getXMLChildElements().size()==0){
				try {
					return browser.browser().findElements(xtj.locator(xn.getBy(), xn.getValue()));
				} catch (Exception e) {
					logger.error("定位元素["+id+"]的时候,出现错误！");
					e.printStackTrace();
				}
			}else{
				int index = 0;
				if(xn.getIndex()!=null){
					try{
						index=Integer.parseInt(xn.getIndex());
					}catch(NumberFormatException e){
						logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
						e.printStackTrace();
					}
				}
				try {
					we=browser.browser().findElements(xtj.locator(xn.getBy(), xn.getValue())).get(index);
				} catch (Exception e) {
					logger.error("定位元素["+id+"]的时候,出现错误！");
					e.printStackTrace();
				}
				for(int i=0;i<xn.getXMLChildElements().size();i++){
					if(i==xn.getXMLChildElements().size()-1){
						XMLChildElement xce = xn.getXMLChildElements().get(i);
						try {
							return we.findElements(xtj.locator(xce.getBy(), xce.getValue()));
						} catch (Exception e) {
							logger.error("定位元素["+id+"]的时候,出现错误！");
							e.printStackTrace();
						}
					}else{
						int cindex =0;
						XMLChildElement xce = xn.getXMLChildElements().get(i);
						if(xce.getIndex()!=null){
							try{
								index=Integer.parseInt(xn.getIndex());
							}catch(NumberFormatException e){
								logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
								e.printStackTrace();
							}
						}
						try{
							we=we.findElements(xtj.locator(xce.getBy(), xce.getValue())).get(cindex);
						}catch(Exception e){
							logger.error("定位元素["+id+"]的时候,出现错误！");
							e.printStackTrace();
						}
					}
				}
			}
		} else if(xn.getElement().getParent().getName().equals("frame")){
			int pindex = 0;
			XMLFrame xf = new XMLFrame();
			xf.setElement(xn.getElement().getParent());
			if(xf.getIndex()!=null){
				try{
					pindex=Integer.parseInt(xn.getIndex());
				}catch(NumberFormatException e){
					logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
					e.printStackTrace();
				}
			}
			try{
				//Window.selectFrame(AutoBase.driver().findElements(xtj.locator(xf.getBy(), xf.getValue())).get(pindex));
				browser.frame(browser.browser().findElements(xtj.locator(xf.getBy(), xf.getValue())).get(pindex));
			}catch(Exception e){
				logger.error("定位元素["+id+"]的时候,出现错误！进行切换frame的时候定位出错！");
				e.printStackTrace();
			}
			if(xn.getXMLChildElements().size()==0){
				try {
					return browser.browser().findElements(xtj.locator(xn.getBy(), xn.getValue()));
				} catch (Exception e) {
					logger.error("定位元素["+id+"]的时候,出现错误！");
					e.printStackTrace();
				}
			}else{
				int index = 0;
				if(xn.getIndex()!=null){
					try{
						index=Integer.parseInt(xn.getIndex());
					}catch(NumberFormatException e){
						logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
						e.printStackTrace();
					}
				}
				try {
					we=browser.browser().findElements(xtj.locator(xn.getBy(), xn.getValue())).get(index);
				} catch (Exception e) {
					logger.error("定位元素["+id+"]的时候,出现错误！");
					e.printStackTrace();
				}
				for(int i=0;i<xn.getXMLChildElements().size();i++){
					if(i==xn.getXMLChildElements().size()-1){
						XMLChildElement xce = xn.getXMLChildElements().get(i);
						try {
							return we.findElements(xtj.locator(xce.getBy(), xce.getValue()));
						} catch (Exception e) {
							logger.error("定位元素["+id+"]的时候,出现错误！");
							e.printStackTrace();
						}
					}else{
						int cindex =0;
						XMLChildElement xce = xn.getXMLChildElements().get(i);
						if(xce.getIndex()!=null){
							try{
								index=Integer.parseInt(xn.getIndex());
							}catch(NumberFormatException e){
								logger.error("元素["+id+"]的index值不能够被转化成为int类型，index的属性值输入错误！");
								e.printStackTrace();
							}
						}
						try{
							we=we.findElements(xtj.locator(xce.getBy(), xce.getValue())).get(cindex);
						}catch(Exception e){
							logger.error("定位元素["+id+"]的时候,出现错误！");
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return null;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public SElement sElement(String id,String path){
		return new SElement(element(id,path));
	}
	
	public List<SElement> sElements(String id,String path){
		List<SElement> slist =new ArrayList<SElement>();
		for(WebElement we:elements(id,path)){
			slist.add(new SElement(we));
		}
		return slist;
	}

	public IBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(IBrowser browser) {
		this.browser = browser;
	}
	
	
	
	
	
}
