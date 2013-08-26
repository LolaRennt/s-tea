package com.github.lmm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.lmm.listener.ActionListener;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Listener {
	Class<? extends ActionListener>[] value();
}
