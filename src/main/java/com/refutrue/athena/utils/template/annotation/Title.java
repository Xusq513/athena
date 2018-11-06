package com.refutrue.athena.utils.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* <p>Title: Title</p>  
* <p>Description: 
* 	根据此title生成数据库描述字段以及前端显示字段的内容
* </p>  
* @author Xusq  
* @date 2018年11月5日
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Title {
	
	public String value();
}
