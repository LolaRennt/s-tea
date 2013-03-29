package org.sky.auto.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Element;

public class XMLList implements XMLNode{
	private Element e;

	public Element getElement() {
		return e;
	}

	public void setElement(Element e) {
		this.e = e;
	}
	
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
}
