package com.refutrue.athena.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射通用工具类
* <p>Title: ReflectUtil</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月2日
 */
public class ReflectUtil {
	
	public static<T> Map<String,Object> bean2Map(Class<T> cls, T t){
		Map<String,Object> returnMap = new HashMap<>();
		Field[] fields = cls.getDeclaredFields();
		for(Field field:fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(t);
				if(value != null) {
					returnMap.put(field.getName(),value);
				}
			}catch (Exception e) {}
			
		}
		return returnMap;
	}
	
}
