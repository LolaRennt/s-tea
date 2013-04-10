package org.sky.auto.xml;

import java.util.Iterator;
import org.dom4j.Element;

public class XMLList implements XMLNode{
	private Element e;

	public Element getElement() {
		return e;
	}

	public void setElement(Element e) {
		this.e = e;
	}
	
	public XMLElement getXMLElement(){
		Iterator<?> iter = e.elementIterator("element");
		XMLElement xe=null;
		while(iter.hasNext()){
			Element ee=(Element) iter.next();
			if(ee.getParent().isRootElement()){
				xe = new XMLElement();
				xe.setElement(ee);
				xe.setListElement(true);
			}
		}
		return xe;
	}
	
	
	public XMLFrame getXMLFrame(){
		XMLFrame xf = null;
		Iterator<?> iter = e.elementIterator("frame");
		while(iter.hasNext()){
			Element ee=(Element) iter.next();
			if(ee.getParent().isRootElement()){
				xf = new XMLFrame();
				xf.setElement(ee);
			}
		}
		return xf;
	}
	
	
	public XMLElement getFrameXMLElement(){
		XMLFrame xf = getXMLFrame();
		XMLElement xe = null;
		if(xf!=null){
			xe = xf.getXMLElement();
			if(xe!=null){
				xe.setFrameElement(true);
				xe.setListElement(true);
			}
		}
		
		return xe;
	}
	
	
	
	
	
}
