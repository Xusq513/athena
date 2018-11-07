package com.refutrue.athena.utils.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日期控件
* <p>Title: Calender</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月7日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Calender {
	
	public String javaFormatter() default "";
	
}
