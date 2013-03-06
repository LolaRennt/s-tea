package org.sky.auto.base;
/**调用page的时候可以通过这个类来实现*/
public class PageModel {
	
	public static <T> T load(Class<T> clazz){
		return AutoBase.page().load(clazz);
	}
}
