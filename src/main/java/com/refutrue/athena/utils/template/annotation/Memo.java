package com.refutrue.athena.utils.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 生成注释所用的注解
 * 默认全局GlobalConfig.properties配置中的memo配置，如果配置了此注解，以此注解为准
* <p>Title: Memo</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月6日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Memo {
	
	public String author() default "";
	
	public String dateFormat() default "";
}
