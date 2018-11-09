package com.refutrue.athena.utils.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 根据接口取已经注入的实例时，有的时候可能会要求顺序，就加入此注解，数字越小排序越前
* <p>Title: Order</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月9日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

	public int value();
}
