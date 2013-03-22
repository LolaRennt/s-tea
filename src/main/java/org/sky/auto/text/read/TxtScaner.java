package org.sky.auto.text.read;

import java.io.File;
import java.util.List;

import org.sky.auto.base.MyFile;

public class TxtScaner {
	private String basepath;
	public TxtScaner(String basepath){
		this.setBasepath(basepath);
	}
	public TxtScaner(){
		this.setBasepath("xml"+File.separator);
	}
	public String getBasepath() {
		return basepath;
	}
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	
	public List<String> getTXTFiles(){
		
		MyFile myfile = new MyFile();
		return myfile.listFile(new File(getBasepath()), "txt", true);
		
	}
	
}	
