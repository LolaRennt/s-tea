package org.sky.auto.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用来标记方法的名字，可以使用story的模式来进行编写脚本，标记后的page类不需要再进行导入操作。
 * 
 * */



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface PageStory {
	String value() default "";
}
