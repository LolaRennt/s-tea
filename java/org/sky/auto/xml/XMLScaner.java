package org.sky.auto.xml;

import java.io.File;
import java.util.List;

import org.sky.auto.base.MyFile;


public class XMLScaner {
	private String basepath;
	public XMLScaner(String basepath){
		this.setBasepath(basepath);
	}
	public XMLScaner(){
		this.setBasepath("xml"+File.separator);
	}
	public String getBasepath() {
		return basepath;
	}
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	
	public List<String> getXMLFiles(){
		
		MyFile myfile = new MyFile();
		return myfile.listFile(new File(getBasepath()), "xml", true);
		
	}
	
}
