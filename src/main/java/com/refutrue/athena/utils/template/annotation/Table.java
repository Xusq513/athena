package com.refutrue.athena.utils.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <p>
 * Title: TableName
 * </p>
 * <p>
 * Description: 默认实体与数据库表的转化关系在GlobalConfig.properties中 此注解实现的是在全局配置上的个性化，个例
 * </p>
 * 
 * @author Xusq
 * @date 2018年11月5日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	
	/**
	 * 表名称
	 */
	public String tableName() default "";
	
	/**
	 * 表空间设置
	 */
	public String tableSpace() default ""; 
	
	/**
	 * 数据库引擎类型
	 */
	public String tableEngine() default "";
}
