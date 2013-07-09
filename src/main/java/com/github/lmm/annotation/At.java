package com.github.lmm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个类用来定义一个URL来配合sourcepage来进行页面的直接性切换。可以提供url的部分内容，能够匹配到唯一就行。
 * 
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface At {
	String value();
}
