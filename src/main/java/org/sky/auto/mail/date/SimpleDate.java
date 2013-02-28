package org.sky.auto.mail.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDate {
	public static String getNowDateFormat(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD-[hh：mm]-");
		Date date = new Date();
		return sdf.format(date);
	}
	
	
	public static String getTodayFormat(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		Date date = new Date();
		return sdf.format(date);
	}
	
	public static String getSimpleDateFormat(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
}
