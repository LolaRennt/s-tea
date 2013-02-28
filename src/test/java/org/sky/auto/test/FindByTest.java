package org.sky.auto.test;


import java.lang.reflect.Field;


import org.sky.auto.anno.Location;
import org.sky.auto.element.Locator;
import org.sky.auto.intrumentation.ClassPool;


public class FindByTest {
	
	@Location(type=Locator.MIX, value = "123")
	private Locator locator;
	public static void main(String[] args) {
		for(Class<?>clazz:ClassPool.getClassPool()){
			for(Field f:clazz.getDeclaredFields()){
				if(f.isAnnotationPresent(Location.class)){
					Locator locator = f.getAnnotation(Location.class).type();
					System.out.println(locator.toString());
					try {
						f.set(clazz.newInstance(), locator);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
			}
		}
	}
}
