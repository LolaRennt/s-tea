package org.sky.auto.log;

import java.text.DateFormat;
import java.util.Date;
/**
 * 一个很简单的logger类，只是实现了在控制台输出相关的信息，可以扩展处打印到文件中，也可以换做log4j<br>
 * 暂时使用的一个log类而已，有需要的可以按照自己的需求配置log。此框架暂时不支持全日志形式，需要日志的话<br>
 * 可以使用Mylogger来进行添加。
 * @author 王天庆
 * */
public class MyLogger {
	private String name;
	private Class<?> clazz;
	Date now = new Date();
	DateFormat df = DateFormat.getDateTimeInstance();
	String logTime = df.format(now);
	public MyLogger(String name) {
		this.name = name;
	}
	public static MyLogger getLogger(String name) {
		return new MyLogger(name);
	}
	public static MyLogger getLogger(Class<?> clazz) {
		return new MyLogger(clazz);
	}
	MyLogger(Class<?> clazz) {
		this.clazz = clazz;
	}
	public void error(Object message) {		
		System.out.println("#["+logTime+"]");
		System.out.print("[ERROR]+++++>");
		outputClazzInfo();
		System.out.println(message);
	}
	public void info(Object message) {		
		System.out.print("#["+logTime+"]");
		System.out.print("[INFO]----->");
		outputClazzInfo();
		System.out.println(message);
	}
	public void warn(Object message) {		
		System.out.print("#["+logTime+"]");
		System.out.print("[WARN]----->");
		outputClazzInfo();
		System.out.println(message);
	}

	public void outputClazzInfo() {
		if (name != null)
			System.out.print(name + ":");
		if (clazz != null)
			System.out.print(clazz.getName()+"->");
	}
}