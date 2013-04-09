package org.sky.auto.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class XMLChildElement implements XMLNode{
	private Element e;
	public XMLChildElement(Element e){
		this.e=e;
	}
	public XMLChildElement(){
		
	}
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
	
//	public List<XMLChildElement> getXMLChildElement(){
//		List<XMLChildElement> clist = new ArrayList<XMLChildElement>();
//		Iterator<?> iter = e.elementIterator();
//		while(iter.hasNext()){
//			Element ce = (Element) iter.next();
//			XMLChildElement xce = new XMLChildElement();
//			xce.setElement(ce);
//			clist.add(xce);
//			getXMLChildElement(ce);
//		}
//	}
	
	
	
	
}
