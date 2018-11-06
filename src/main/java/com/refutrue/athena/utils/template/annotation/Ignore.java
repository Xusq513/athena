package com.refutrue.athena.utils.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 加上此标识，代表此属性，不是表单内容
* <p>Title: Ignore</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月5日
 */
public @interface Ignore {

}
