package org.sky.auto.text.read;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sky.auto.base.AutoBase;
import org.sky.auto.exception.MyAutoException;
import org.sky.auto.exception.MyElementNotFoundException;
import org.sky.auto.window.Window;
import org.sky.auto.xml.XmlToJavaTools;

/**
 * 这个类用来存储元素的层级关系，使用list，里面存了每个元素的层级关系。
 * */
public class ChainLink{
	private static Logger logger = Logger.getLogger(ChainLink.class);
	private Map<By,Integer> bychain;
	private String type;
	private Map<By,Integer> framechain;
	private boolean isframeElement=false;
	public ChainLink(){
		bychain=new LinkedHashMap<By,Integer>();
		framechain=new LinkedHashMap<By,Integer>();
	}
	/**存储数据链，一个对象对应一个元素*/
	public void save(String... locations){
		XmlToJavaTools tool = new XmlToJavaTools();
		for(String str:locations){
			Integer index=0;
			if(str.toLowerCase().contains(",")){
				index=Integer.parseInt(str.split(",")[1].split(Block.VALUTION)[1]);
				By by=tool.locator(str.split(",")[0].split(Block.VALUTION)[0], 
					TXTFormat.format(str.split(",")[0].split(Block.VALUTION)[1]));
				bychain.put(by, index);
			}else{
				By by=tool.locator(str.split(Block.VALUTION)[0],TXTFormat.format(str.split(Block.VALUTION)[1]));
				bychain.put(by, index);
			}
		}
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**判断元素是否在一个frame里面，链的一个属性*/
	public boolean isFrameElement(){
		return isframeElement;
		
	}
	/**设置frame是否存在的属性*/
	public void setIsFrameElement(boolean bool){
		this.isframeElement=bool;
	}
	
	public void saveFrame(String... locations){
		XmlToJavaTools tool = new XmlToJavaTools();
		for(String str:locations){
			Integer index=0;
			if(str.toLowerCase().contains(",")){
				index=Integer.parseInt(str.split(",")[1].split(Block.VALUTION)[1]);
				By by = tool.locator(str.split(",")[0].split(Block.VALUTION)[0], 
						TXTFormat.format(str.split(",")[0].split(Block.VALUTION)[1]));
				framechain.put(by, index);
			}else{
				By by=tool.locator(str.split(Block.VALUTION)[0],TXTFormat.format(str.split(Block.VALUTION)[1]));
				framechain.put(by, index);
			}
		}
	}
	/**可以将链的形式转化为WebElement的形式*/
	public WebElement element(){
		Window.selectDefaultWindow();
		Map<By,Integer> map = getBychain();
		Map<By,Integer> frameMap=getFramechain();
		if(!getType().toLowerCase().contains("list")){
			if(!isFrameElement()){
				WebElement element=null;
				int i=0;
				Iterator<Entry<By,Integer>> mapEntry=map.entrySet().iterator();
				while(mapEntry.hasNext()){
					Entry<By,Integer> entry = mapEntry.next();
					By by = entry.getKey();
					Integer index = entry.getValue();
					try{
						if(map.size()==1){
							element=AutoBase.driver().findElements(by).get(index);
						}else{
							if(i==0){
								element=AutoBase.driver().findElements(by).get(index);
							}else{
								element=element.findElements(by).get(index);
							}
						}
					}catch(Exception e){
						logger.error("在查找元素的时候出现了错误，请检查元素定位是否错误！",e);
						throw new MyElementNotFoundException("在查找元素的时候出现了错误，请检查元素定位是否错误！");
					}
					
					i++;
				}
				return element;
			}else if(isFrameElement()){
				int i=0;
				int j=0;
				WebElement element = null;
				Iterator<Entry<By,Integer>> frameMapEntry =frameMap.entrySet().iterator();
				while(frameMapEntry.hasNext()){
					Entry<By,Integer> frameEntry=frameMapEntry.next();
					By by=frameEntry.getKey();
					Integer index = frameEntry.getValue();
					try{
						if(frameMap.size()==1){
							Window.selectFrame(AutoBase.driver().findElements(by).get(index));
							//System.out.println("切入进了Frame");
						}else{
							if(i==0){
								element=AutoBase.driver().findElements(by).get(index);
							}else{
								element=element.findElements(by).get(index);
							}			
						}
					}catch(Exception e){
						logger.error("切入到Frame的时候出现了错误，请确认Frame的定位是否正确",e);
						throw new MyElementNotFoundException("切入到Frame的时候出现了错误，请确认Frame的定位是否正确");
					}
					
					i++;
				}
				Iterator<Entry<By,Integer>> mapEntry=map.entrySet().iterator();
				while(mapEntry.hasNext()){
					Entry<By,Integer> entry = mapEntry.next();
					By by = entry.getKey();
					Integer index = entry.getValue();
					//System.out.println(by.toString());
					try{
						if(map.size()==1){
							element=AutoBase.driver().findElements(by).get(index);					
						}else{
							if(j==0){
								element=AutoBase.driver().findElements(by).get(index);
							}else{
								element=element.findElements(by).get(index);
							}
						}
					}catch(Exception e){
						logger.error("没有找到Frame内的元素，请检查资源文档并确定定位是否正确！",e);
						throw new MyElementNotFoundException("没有找到Frame内的元素，请检查资源文档并确定定位是否正确！");
					}
					
					j++;
				}
				return element;
			}
		}else{
			logger.error("此元素的类型为List,不能够转化为WebElement的元素，请检查资源文件没有异常情况");
			throw new MyAutoException("此元素的类型为List,不能够转化为WebElement的元素，请检查资源文件没有异常情况");
		}
		return null;
	}
	public Map<By, Integer> getBychain() {
		return bychain;
	}
	public void setBychain(Map<By, Integer> bychain) {
		this.bychain = bychain;
	}
	public Map<By, Integer> getFramechain() {
		return framechain;
	}
	public void setFramechain(Map<By, Integer> framechain) {
		this.framechain = framechain;
	}
	
	/**获取元素列表
	 * @return 返回一个list形式的元素列表
	 * */
	public List<WebElement> elements(){
		Window.selectDefaultWindow();
		Map<By,Integer> map = getBychain();
		Map<By,Integer> frameMap=getFramechain();
		if(getType().toLowerCase().contains("list")){
			if(!isFrameElement()){
				Window.selectDefaultWindow();
				WebElement element=null;
				int i=0;
				Iterator<Entry<By,Integer>> mapEntry=map.entrySet().iterator();
				while(mapEntry.hasNext()){
					Entry<By,Integer> entry = mapEntry.next();
					By by = entry.getKey();
					Integer index = entry.getValue();
					if(map.size()==1){
						return AutoBase.driver().findElements(by);
					}else{
						if(i==0){
							element=AutoBase.driver().findElements(by).get(index);
						}else if(i==map.size()-1){
							return element.findElements(by);
						}else{
							element=AutoBase.driver().findElements(by).get(index);
						}
					}
					i++;
				}
			}else if(isFrameElement()){
				int i=0;
				int j=0;
				WebElement element = null;
				Iterator<Entry<By,Integer>> frameMapEntry =frameMap.entrySet().iterator();
				while(frameMapEntry.hasNext()){
					Entry<By,Integer> frameEntry=frameMapEntry.next();
					By by=frameEntry.getKey();
					Integer index = frameEntry.getValue();
					if(frameMap.size()==1){
						Window.selectFrame(AutoBase.driver().findElements(by).get(index));
					}else{
						if(i==0){
							element=AutoBase.driver().findElements(by).get(index);
						}else{
							element=element.findElements(by).get(index);
							Window.selectFrame(element);
						}
					}
					i++;
				}
				Iterator<Entry<By,Integer>> mapEntry=map.entrySet().iterator();
				while(mapEntry.hasNext()){
					Entry<By,Integer> entry = mapEntry.next();
					By by = entry.getKey();
					Integer index = entry.getValue();
					if(map.size()==1){
						return element.findElements(by);
					}else{
						if(j==map.size()-1){
							return element.findElements(by);
						}else{
							element=element.findElements(by).get(index);
						}
					}
					j++;
				}
			}
		}else{
			logger.error("没有找到匹配的type类型,如果是List的形式，请加入Type:List的说明");
			throw new MyAutoException("没有找到匹配的type类型,如果是List的形式，请加入Type:List的说明");
		}
		return null;
	}
}