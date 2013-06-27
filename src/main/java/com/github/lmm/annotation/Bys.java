package com.github.lmm.annotation;

import com.github.lmm.element.Locator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bys {

    Locator locator();
    String  value();
    int index() default 0;
    String commit();
}