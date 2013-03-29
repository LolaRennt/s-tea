package org.sky.auto.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Element;



public class XMLElements implements XMLNode{
	private XMLDocument xd;
	static Logger logger = Logger.getLogger(XMLElements.class);
	public XMLElements(XMLDocument xd){
		this.xd=xd;
		if(xd!=null){
			e=xd.getXMLElements();
		}else{
			logger.error("加载资源"+this.xd.getPath()+"的时候出现错误！");
		}
		
	}
	public Element getElement() {
		return e;
	}

	public void setElement(Element e) {
		this.e = e;
	}
	private Element e;
	
	public List<XMLElement> getXMLElementList(){
		List<XMLElement> elist = new ArrayList<XMLElement>();
		Iterator<?> iter = e.elementIterator("element");
		while(iter.hasNext()){
			Element ee=(Element) iter.next();
			if(ee.getParent().isRootElement()){
				XMLElement xe = new XMLElement();
				xe.setElement(ee);
				elist.add(xe);
			}
		}
		return elist;
	}
	
	public XMLElement getXMLElement(String id){
		for(XMLElement xe:getXMLElementList()){
			if(xe.getElement().attributeValue("id").equals(id)){
				return xe;
			}
		}
		return null;
	}
	
	public List<XMLFrame> getXMLFrameList(){
		List<XMLFrame> elist = new ArrayList<XMLFrame>();
		Iterator<?> iter = e.elementIterator("frame");
		while(iter.hasNext()){
			Element ee=(Element) iter.next();
			if(ee.getParent().isRootElement()){
				XMLFrame xf = new XMLFrame();
				xf.setElement(ee);
				elist.add(xf);
			}
		}
		return elist;
	}
	
	public XMLFrame getXMLFrame(String id){
		for(XMLFrame xf : getXMLFrameList()){
			if(xf.getElement().attributeValue("id").equals(id)){
				return xf;
			}
		}
		return null;
	}
	
	public List<XMLList> getXMLLists(){
		List<XMLList> elist = new ArrayList<XMLList>();
		Iterator<?> iter = e.elementIterator("list");
		while(iter.hasNext()){
			Element ee = (Element) iter.next();
			if(ee.isRootElement()){
				XMLList xl = new XMLList();
				xl.setElement(ee);
				elist.add(xl);
			}
		}
		return elist;
	}
	
	public XMLList getXMLList(String id){
		for(XMLList xl : getXMLLists()){
			if(xl.getElement().attributeValue("id").equals(id)){
				return xl;
			}
		}
		return null;
	}
	
	public List<XMLNode> getXMLNodes(){
		List<XMLNode> xnlist = new ArrayList<XMLNode>();
		if(getXMLElementList().size()!=0){
			for(XMLElement xe:getXMLElementList()){
				xnlist.add(xe);
			}
		}
		if(getXMLFrameList().size()!=0){
			for(XMLFrame xf: getXMLFrameList()){
				xnlist.add(xf);
			}
		}
		if(getXMLLists().size()!=0){
			for(XMLList xl : getXMLLists()){
				xnlist.add(xl);
			}
		}
		return xnlist;
	}
	/**这个方法里面搜索的Elmenet包括所有的Element元素*/
	public XMLNode getNode(String id){
		if(getXMLNodes().size()!=0){
			for(int i=0; i<getXMLNodes().size();i++){
				if(getXMLNodes().get(i).getElement().attributeValue(id).equals(id)){
					return getXMLNodes().get(i);
				}
			}
		}
		return null;
	}
	
	
	public List<XMLElement> getAllXMLElment(){
		List<XMLElement> elist =new ArrayList<XMLElement>();
		if(getXMLElementList().size()!=0){
			for(XMLElement xe: getXMLElementList()){
				elist.add(xe);
			}
		}
		if(getXMLFrameList().size()!=0){
			for(XMLFrame xf: getXMLFrameList()){
				if(xf.getXMLElement()!=null){
					elist.add(xf.getXMLElement());
				}
			}
		}
		return elist;
	}
	
	public XMLElement getXMLElementOfAll(String id){
		if(getAllXMLElment().size()!=0){
			for(XMLElement xe:getAllXMLElment()){
				if(xe.getId().equals(id)){
					return xe;
				}
			}
		}
		return null;
	}
	public XMLDocument getXMLDocument() {
		return xd;
	}
	public void setXMLDocument(XMLDocument xd) {
		this.xd = xd;
	}
	
}
