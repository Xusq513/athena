package com.refutrue.athena.utils.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Select组件，用于后端的显示和前端数据的展示
* <p>Title: Select</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月9日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {

	/**
	 * 数据字典请求的url，主要用于前端ajax地址的配置
	 */
	public String url() default "/Dic/type";
	
	/**
	 * 后端展示需要查询的组件，该组件是Spring Bean Name,要求实现ISelect接口
	 */
	public String component() default "dicServiceImpl";
	
	/**
	 * 字典项
	 */
	public String type();
}
