package org.sky.auto.text.read;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StringContorl {
	
	
	public int CharDisappearTimes(String str,String chars){
		int times=0;
		while(true){
			if(chars.indexOf(str)!=-1){
				times++;
				chars=chars.substring(chars.indexOf(str)+1,chars.length());
			}else if(chars.indexOf(str)==-1){
				break;
			}
		}
		return times;
	}
	 public static String replaceBlank(String str) {
		 String dest = "";
		 if (str!=null) {
			 Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			 Matcher m = p.matcher(str);
			 dest = m.replaceAll("");
		 }
		 return dest;
	 }
}
