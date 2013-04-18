package org.sky.auto.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.dom4j.Attribute;
import org.dom4j.Element;


public class XMLFrame implements XMLNode{
	
	private Element e;
	private XMLElement xelement;
	public Element getElement() {
		return e;
	}

	public void setElement(Element e) {
		this.e = e;
	}

	@SuppressWarnings("unchecked")
	public Map<String,String> getAttributes(){
		Map<String,String> amap = new HashMap<String,String>();
		List<Attribute> alist = getElement().attributes();
		for(int i=0;i<alist.size();i++){
			amap.put(alist.get(i).getName(), alist.get(i).getValue());
		}
		return amap;
	}
	
	public String getId(){
		return getAttributes().get("id");
	}
	
	public String getValue(){
		return getAttributes().get("value");
	}
	
	public String getIndex(){
		return getAttributes().get("index");
	}
	
	public String getBy(){
		return getAttributes().get("by");
	}
	
	@SuppressWarnings("unchecked")
	public List<Attribute> getAttributeList(){
		return getElement().attributes();
	}
	
	public XMLElement getXMLElement(){
		collectXMLElement(getElement());
		return this.xelement;
	}
	
	private void collectXMLElement(Element e){
		Iterator<?> iter = e.elementIterator();
		while(iter.hasNext()){
			Element xe = (Element) iter.next();
			if(xe.getName().toLowerCase().equals("element")){
				xelement=new XMLElement();
				xelement.setElement(xe);
				xelement.setFrameElement(true);
				break;
			}else{
				collectXMLElement(xe);
			}
		}
		
	}
	
	
	
	
	
}
