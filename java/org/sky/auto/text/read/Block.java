package org.sky.auto.text.read;




/**
 * @author 王天庆
 * 这个类是用来解析txt文本元素定义的块元素的
 * */
public  class Block {
	public  static String BLOCK_START="{";
	public  static String BLOCK_END="}";
	public  static String ELEMENT_LINK="->";
	public  static String TYPE="TYPE";
	public  static String SPLIT=";";
	public  static String VALUTION=":";
	

	public static String getVALUTION() {
		return VALUTION;
	}

	public static void setVALUTION(String vALUTION) {
		VALUTION = vALUTION;
	}

	public static String getBLOCK_START() {
		return BLOCK_START;
	}

	public static void setBLOCK_START(String bLOCK_START) {
		BLOCK_START = bLOCK_START;
	}

	public static String getBLOCK_END() {
		return BLOCK_END;
	}

	public static void setBLOCK_END(String bLOCK_END) {
		BLOCK_END = bLOCK_END;
	}

	public static String getELEMENT_LINK() {
		return ELEMENT_LINK;
	}

	public static void setELEMENT_LINK(String eLEMENT_LINK) {
		ELEMENT_LINK = eLEMENT_LINK;
	}

	public static String getTYPE() {
		return TYPE;
	}

	public static void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public static String getSpilt() {
		return SPLIT;
	}

	public static void setSplit(String split) {
		SPLIT = split;
	}
	
	
	
	
	
	
}
