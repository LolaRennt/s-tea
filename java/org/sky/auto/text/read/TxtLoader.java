package org.sky.auto.text.read;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sky.auto.load.SourceLoader;

public class TxtLoader {
	private static Map<String,ChainLink> map = new HashMap<String,ChainLink>();
	private static Logger logger = Logger.getLogger(TxtLoader.class);
	
	public static List<String> getTxts(){
		TxtScaner ts=new TxtScaner();
		return ts.getTXTFiles();
	}
	
	/**收集txt文件的信息资源*/
	public static Map<String,ChainLink> load(){
		List<String> txtfiles=getTxts();
		if(map.size()==0){
			logger.info("开始扫描txt的资源元素...");
			for(String txtfile:txtfiles){
				ReadFromFile rff = new ReadFromFile(txtfile);
				if(rff.isFrameBlockFile()){
					List<StringFrameBlock> fblocks=rff.getStringFrameBlockList();
					for(StringFrameBlock fblock :fblocks){
						SourceLoader.add(fblock);
						map.put(fblock.getLocationName(), fblock.getLocation());
						logger.info("扫描收集了资源->"+fblock.getLocationName());
					}
				}else{
					List<StringBlock> blocks=rff.getStringBlockList();
					for(StringBlock block:blocks){
						SourceLoader.add(block);
						map.put(block.getLocationName(), block.getLocation());
						logger.info("扫描收集了资源->"+block.getLocationName());
					}
				}		
			}
			logger.info("txt资源扫描结束");
		}else{
			return map;
		}
		
		return map;
	}
	
	
}
