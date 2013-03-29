package org.sky.auto.base;
/**调用page的时候可以通过这个类来实现*/
public class PageModel {
	/**@param clazz 我们编写的Page类
	 * @return 返回自己编写的类的对象
	 * */
	public static <T> T load(Class<T> clazz){
		return AutoBase.page().load(clazz);
	}
}
