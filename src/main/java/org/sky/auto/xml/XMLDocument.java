package org.sky.auto.xml;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public  class XMLDocument {
	static Logger logger = Logger.getLogger(XMLDocument.class);
	private Document doc;
	private String path;
	public XMLDocument(String path){
		this.setPath(path);
		SAXReader sr = new SAXReader();
		try {
			this.setDoc(sr.read(new File(path)));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		//logger.info("加载资源XML文件地址为："+path);
	}
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public Element getXMLElements(){
		//System.out.println(doc.getRootElement().getName());
		return doc.getRootElement();
	}

}
