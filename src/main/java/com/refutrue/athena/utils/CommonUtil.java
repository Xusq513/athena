package com.refutrue.athena.utils;

import java.util.List;

/**
 * 常用的工具类
* <p>Title: CommonUtil</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月8日
 */
public class CommonUtil {

	/**
	 * 判断list是否为空
	 * @param list
	 * @return true 为空
	 */
	public static boolean isNullList(List<?> list) {
		if(list == null || list.size() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNullList(List<?> list) {
		return !isNotNullList(list);
	}
}
