package org.sky.auto.verify;


public class Assert extends org.junit.Assert{
	
	public boolean shouldContain(String str1,String str2){
		return str1.contains(str2);
	}
	
	public boolean shouldBeString(Object obj){
		if(obj instanceof String){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean shouldBeNumbers(Object obj){
		try{
			Integer.parseInt(obj.toString());
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	
	public boolean shouldBeInteger(Object obj){
		if(obj instanceof Integer){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean shouldBeStartWith(String str1,String str2){
		if(str1.startsWith(str2)){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
	
}
