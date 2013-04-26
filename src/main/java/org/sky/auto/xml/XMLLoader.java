package org.sky.auto.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sky.auto.load.SourceLoader;
import org.sky.auto.report.RunTimeMethod;





public class XMLLoader {
	private static Logger logger = Logger.getLogger(XMLLoader.class);
	private static Map<String,XMLElement> xmlmap = new HashMap<String,XMLElement>();
	private static Map<String, XMLList> xlmap = new HashMap<String,XMLList>();
	public static List<String> getXMLs(String path){
		XMLScaner scaner = new XMLScaner(path);
		return scaner.getXMLFiles();
	}
	
	public static Map<String, XMLElement> load(String basepath){
		if(xmlmap.size()==0){
			logger.info("["+RunTimeMethod.getName()+"]"+"开始对xml资源进行扫描....");
			List<String> list = getXMLs(basepath);
			for(String path:list){
				XMLDocument xd = new XMLDocument(path);
				XMLElements xe = new XMLElements(xd);
				for(XMLElement xn :xe.getAllXMLElement()){
					SourceLoader.add(xn);
					xmlmap.put(xn.getId().trim(),xn);
					logger.info("["+RunTimeMethod.getName()+"]"+"扫描收集了资源->"+xn.getId());
				}
			}
			//Log.Debug("查找到"+xmlmap.size()+"个元素！");
			logger.info("["+RunTimeMethod.getName()+"]"+"扫描XML资源完毕...");
		}		
		return xmlmap;
	}
	
	public static XMLElement getXMLElement(String id){
		return xmlmap.get(id);
	}
	
	/**清空xml加载器的资源*/
	public static void clear(){
		xmlmap.clear();
	}
	
	public static Map<String, XMLElement> load(){
		if(xmlmap.size()==0){
			logger.info("["+RunTimeMethod.getName()+"]"+"开始对xml资源进行扫描....");
			List<String> list = getXMLs("xml");
			for(String path:list){
				XMLDocument xd = new XMLDocument(path);
				XMLElements xe = new XMLElements(xd);
				for(XMLElement xn :xe.getAllXMLElement()){
					SourceLoader.add(xn);
					xmlmap.put(xn.getId(),xn);
					logger.info("["+RunTimeMethod.getName()+"]"+"扫描收集了资源->"+xn.getId());
				}
			}
			//Log.Debug("查找到"+xmlmap.size()+"个元素！");
			logger.info("["+RunTimeMethod.getName()+"]"+"扫描XML资源完毕...");
		}else{
			return xmlmap;
		}
		return xmlmap;
	}
	
	public static Map<String,XMLList> loadXMLList(){
		if(xlmap.size()==0){
			logger.info("["+RunTimeMethod.getName()+"]"+"开始对xml的List资源进行扫描....");
			List<String> list = getXMLs("xml");
			for(String path:list){
				XMLDocument xd =new XMLDocument(path);
				XMLElements xe = new XMLElements(xd);
				for(XMLList xl : xe.getXMLList()){
					xlmap.put(xl.getId(), xl);
					logger.info("["+RunTimeMethod.getName()+"]"+"扫描收集了List资源->"+xl.getId());
				}
			}
			logger.info("["+RunTimeMethod.getName()+"]"+"扫描List资源完毕...");
		}else{
			return xlmap;
		}
		return xlmap;
	}
	
	public static XMLList getXMLList(String id){
		return xlmap.get(id);
	}
}
