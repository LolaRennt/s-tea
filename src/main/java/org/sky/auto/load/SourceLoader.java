package org.sky.auto.load;

import java.util.HashMap;
import java.util.Map;

import org.sky.auto.exception.SameIDNameException;
import org.sky.auto.text.read.StringBlock;
import org.sky.auto.text.read.StringFrameBlock;
import org.sky.auto.xml.XMLElement;

import com.github.lmm.core.AutoResetThreadLocal;

public class SourceLoader {
	private static AutoResetThreadLocal<Map<String,Source>> smapthread = new AutoResetThreadLocal<Map<String,Source>>(){
		@Override
		protected Map<String, Source> initialValue() {
			return new HashMap<String,Source>();
		}
	};
	
	private static Map<String,Source> getmap(){
		return smapthread.get();
	}
	
	
	//private static Map<String,Source> smap=new HashMap<String,Source>();
	
	public static void add(XMLElement xe){
		if(getmap().get(xe.getId())==null){
			getmap().put(xe.getId(),Source.XML);
		}else{
			throw new SameIDNameException("收集资源["+xe.getId()+"]的时候出现了错误，可能资源内有重名的资源或者空名资源，请检查资源名称");
		}
	}

	
	public static void add(StringBlock block){
		if(getmap().get(block.getLocationName())==null){
			getmap().put(block.getLocationName(),Source.TXT);
		}else{
			throw new SameIDNameException("收集资源["+block.getLocationName()+"]的时候出现了错误，可能资源内有重名的资源或者空名资源，请检查资源名称");
		}
	}
	
	
	public static void add(StringFrameBlock block){
		if(getmap().get(block.getLocationName())==null){
			getmap().put(block.getLocationName(),Source.TXT);
		}else{
			throw new SameIDNameException("收集资源["+block.getLocationName()+"]的时候出现了错误，可能资源内有重名的资源或者空名资源，请检查资源名称");
		}
	}
	
	
	public static void clear(){
		getmap().clear();
	}
	
	public static Source getSource(String id){
		return getmap().get(id);
	}
	
}



