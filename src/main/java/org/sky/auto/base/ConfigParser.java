package org.sky.auto.base;

import java.io.File;
import java.util.Map;

public class ConfigParser {
	public static Map<String,String> parse(){
		return PropertiesTool.getPropertiesMap("resource"+File.separator+"config.properties");
	}
	
	/**得到浏览器的路径*/
	public static String getPath(){
		return parse().get("browser_path");
	}
	/**得到截屏的路径，如果不存在的话创建一个*/
	public static String getScreenShotPath(){
		String path =parse().get("screenshot_path");
		if(!(new File(path).isDirectory())){
			new File(path).mkdir();
		}
		return path;
	}
	
	public static String getIENode(){
		return parse().get("node-ie");
	}
	
	public static String getFirefoxNode(){
		return parse().get("node-firefox");
	}
	
	public static String getHtmlUnitNode(){
		return parse().get("node-htmlUnit");
	}
	
	public static String getChromeNode(){
		return parse().get("node-chrome");
	}
	
	public static String getSafariNode(){
		return parse().get("node-safari");
	}
	
	public static String getOperaNode(){
		return parse().get("node-safari");
	}
	
	public static String getErrorLogDir(){
		return parse().get("error-log");
	}
	
	public static String getBaseName(){
		return parse().get("base-name");
	}
	
	
	public static Map<String,String> mailParse(){
		return PropertiesTool.getPropertiesMap("mailconfig.properties");
	}
	/**邮箱的配置**/
	public static String getMailHost(){
		return mailParse().get("mail-host");
	}
	
	public static String getMailPort(){
		return mailParse().get("mail-port");
	}
	public static String getMailUsername(){
		return mailParse().get("mail-username");
	}
	
	public static String getMailPassword(){
		return mailParse().get("mail-password");
	}
	
	public static Boolean  getMailValidate(){
		if(mailParse().get("mail-validate").equals("true")){
			return true;
		}else if(mailParse().get("mail-validate").equals("false")){
			return false;
		}else{
			return false;
		}
	}
	
	public static String getFromMail(){
		return mailParse().get("from-email");
				
	}
	
	public static String getToEMail(){
		return mailParse().get("to-email");
	}
	
	public static int getStoryThreads(){
		return Integer.parseInt(storyParse().get("thread-nums"));
	}
	
	public static Map<String,String> storyParse(){
		return PropertiesTool.getPropertiesMap("storyConfig.properties");
	}
	
	
}



