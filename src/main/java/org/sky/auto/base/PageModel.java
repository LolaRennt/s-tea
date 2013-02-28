package org.sky.auto.base;

public class PageModel {
	
	public static <T> T load(Class<T> clazz){
		return AutoBase.page().load(clazz);
	}
}
