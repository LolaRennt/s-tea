package org.sky.auto.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**这个注解用来注释我们邮件的时候定义的邮件地址和需要发送的人员姓名，有利于统一管理。*/
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.TYPE)
public @interface Author {
	String mail() default "";
	String Name(); 
}
