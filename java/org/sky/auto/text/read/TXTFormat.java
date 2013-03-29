package org.sky.auto.text.read;

public class TXTFormat {
	/**冒号*/
	private static String Colon="$col$";
	/**分号*/
	private static String Semicolon="$sem$";
	/**空格*/
	private static String Empty="$emp$";
	/**大括号*/
	private static String BraceLeft="$braleft$";
	private static String BraceRight="$brarigth$";
	/**逗号*/
	private static String Comma="$com$";
	public static String format(String str){
		str=str.replaceAll("\\$col\\$", ":").replaceAll("\\$sem\\$", ";").replaceAll("\\$emp\\$", " ")
				.replaceAll("\\$braleft\\$", "{").replaceAll("\\$braright\\$", "}").replaceAll("\\$com\\$", ",");
		return str;
	}
	public static String getColon() {
		return Colon;
	}
	public static void setColon(String colon) {
		Colon = colon;
	}
	public static String getSemicolon() {
		return Semicolon;
	}
	public static void setSemicolon(String semicolon) {
		Semicolon = semicolon;
	}
	public static String getEmpty() {
		return Empty;
	}
	public static void setEmpty(String empty) {
		Empty = empty;
	}
	public static String getBraceLeft() {
		return BraceLeft;
	}
	public static void setBraceLeft(String braceLeft) {
		BraceLeft = braceLeft;
	}
	public static String getBraceRight() {
		return BraceRight;
	}
	public static void setBraceRight(String braceRight) {
		BraceRight = braceRight;
	}
	public static String getComma() {
		return Comma;
	}
	public static void setComma(String comma) {
		Comma = comma;
	}
	
}
