package org.sky.auto.robot.key;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.github.lmm.intrumentation.ClassPool;


@RobotKeywords
public class PageWordkeys {
	
	@RobotKeyword
	public void pageExecuter(String PageName,String methodName,Object...objects){
		Set<Class<?>>cls=ClassPool.getClassPool();
		for(Class<?>clazz:cls){
			if(clazz.getName().trim().toLowerCase().equals(PageName.trim().toLowerCase())){
				Method[] methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					if(m.getName().toLowerCase().equals(methodName)){
						try {
							m.invoke(clazz.newInstance(),objects);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (InstantiationException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
