package com.refutrue.athena.utils.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

	/**
	 * 是否必填 
	 */
	public boolean required() default false;
	
	/**
	 * 最大长度
	 */
	public int length() default Integer.MAX_VALUE;
	
	/**
	 * 最大值,结合权重进行计算,闭区间
	 */
	public long maxNum() default Long.MAX_VALUE;
	
	/**
	 * 最小值,结合权重进行计算,闭区间
	 */
	public long minNum() default Long.MIN_VALUE;
	
	/**
	 * 权重，结合最大值和最小值进行使用，主要是为了避免丢失精度的计算
	 */
	public int weight() default 1;
	
	/**
	 * 结束时间 时间格式遵循Java，yyyy-MM-dd hh:mm:ss ,"now"特殊值代表现在
	 */
	public String endTime() default "";
	
	/**
	 * 开始时间 时间格式遵循Java，yyyy-MM-dd hh:mm:ss
	 */
	public String startTime() default "";
}
