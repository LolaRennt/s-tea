package org.sky.auto.anno;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.sky.auto.element.Locator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Location {
	Locator type() default Locator.Id;
	String value();
	String id() default "";
	String css() default "";
	String xpath() default "";
	String tagName() default "";
	String linkText() default "";
	String partialLinkText() default "";
	String name() default "";
	String className() default "";
	
}
