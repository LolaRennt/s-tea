package org.sky.auto.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class XMLElement implements XMLNode{
	private static Logger logger = Logger.getLogger(XMLElement.class);
	private Element e;
	/**这个属性存储的是在这个XMLElement元素下面所有的child元素的list*/
	private List<XMLChildElement> elist = new ArrayList<XMLChildElement>();
	private boolean isFrameElement=false;
	private boolean isListElement=false;
	public XMLElement(){
		
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
	
	public String getId(){
		return getAttributes().get("id").trim();
	}
	
	public String getValue(){
		return getAttributes().get("value").trim();
	}
	
	public String getIndex(){
		if(getAttributes().get("index")!=null){
			return getAttributes().get("index").trim();
		}
		return null;
	}
	
	public String getBy(){
		return getAttributes().get("by").trim();
	}
	
	@SuppressWarnings("unchecked")
	public List<Attribute> getAttributeList(){
		return getElement().attributes();
	}
	
	protected List<XMLChildElement> getXMLChildElements(){
		collectChildElments(getElement());
		return elist;
	}
	
	private void collectChildElments(Element en){
		List<?> xlist = en.elements("childElement");
		if (xlist.size() != 0) {      
            for (Iterator<?> it = xlist.iterator(); it.hasNext();) {   
                Element elem = (Element) it.next();
                XMLChildElement xce = new XMLChildElement(elem);
                logger.info("["+this.getId()+"]有子元素,定位方式为:"+"By->"+xce.getBy()+","+"Value->"+xce.getValue()+","+"Index->"+xce.getIndex());
                elist.add(xce);
                //递归遍历   
                collectChildElments(elem);   
            }   
        }   
	}
	public boolean isFrameElement() {
		return isFrameElement;
	}
	public void setFrameElement(boolean isFrameElement) {
		this.isFrameElement = isFrameElement;
	}
	public boolean isListElement() {
		return isListElement;
	}
	public void setListElement(boolean isListElement) {
		this.isListElement = isListElement;
	}
	
}
