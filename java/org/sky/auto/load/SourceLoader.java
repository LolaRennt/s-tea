package org.sky.auto.load;

import java.util.HashMap;
import java.util.Map;

import org.sky.auto.exception.SameIDNameException;
import org.sky.auto.text.read.StringBlock;
import org.sky.auto.text.read.StringFrameBlock;
import org.sky.auto.xml.XMLElement;

public class SourceLoader {
	
	private static Map<String,Source> smap=new HashMap<String,Source>();
	
	public static void add(XMLElement xe){
		if(smap.get(xe.getId())==null){
			smap.put(xe.getId(),Source.XML);
		}else{
			throw new SameIDNameException("收集资源["+xe.getId()+"]的时候出现了错误，可能资源内有重名的资源或者空名资源，请检查资源名称");
		}
	}

	
	public static void add(StringBlock block){
		if(smap.get(block.getLocationName())==null){
			smap.put(block.getLocationName(),Source.TXT);
		}else{
			throw new SameIDNameException("收集资源["+block.getLocationName()+"]的时候出现了错误，可能资源内有重名的资源或者空名资源，请检查资源名称");
		}
	}
	
	
	public static void add(StringFrameBlock block){
		if(smap.get(block.getLocationName())==null){
			smap.put(block.getLocationName(),Source.TXT);
		}else{
			throw new SameIDNameException("收集资源["+block.getLocationName()+"]的时候出现了错误，可能资源内有重名的资源或者空名资源，请检查资源名称");
		}
	}
	
	
	public static void clear(){
		smap.clear();
	}
	
	public static Source getSource(String id){
		return smap.get(id);
	}
	
}



