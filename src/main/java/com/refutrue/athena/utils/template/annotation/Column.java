package com.refutrue.athena.utils.template.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	/**
	 * 字段的名称
	 */
	public String columnName() default "";
	
	/**
	 * 字段的类型
	 */
	public String columnType() default "";
	
	/**
	 * 是否是主键
	 */
	public boolean isPrimaryKey() default false;
	
	/**
	 * 是否非空
	 */
	public boolean isNotNull() default false;
	
	/**
	 * mysql是否自增长
	 */
	public boolean isAutoIncrement() default false;
}
