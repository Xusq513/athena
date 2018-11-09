package com.refutrue.athena.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.refutrue.athena.utils.template.annotation.Title;

/**
 * 反射通用工具类
 * <p>
 * Title: ReflectUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Xusq
 * @date 2018年11月2日
 */
public class ReflectUtil {

	/**
	 * 将一个实体转化为Map
	 * 
	 * @param cls
	 *            实体的类型
	 * @param t
	 *            实体对象
	 * @return
	 */
	public static <T> Map<String, Object> bean2Map(Class<T> cls, T t) {
		Map<String, Object> returnMap = new HashMap<>();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(t);
				if (value != null) {
					returnMap.put(field.getName(), value);
				}
			} catch (Exception e) {
			}

		}
		return returnMap;
	}


	/**
	 * 获取一个类下面的所有字段，包含父类的字段
	 * 
	 * @param cls
	 *            类
	 * @param list
	 *            返回的集合
	 */
	public static void getAllFieldByBean(Class<?> cls, List<Field> list) {
		if (list == null) {
			list = new ArrayList<>();
		}
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			list.add(f);
		}
		if (cls.getSuperclass() == null) {
			return;
		} else {
			getAllFieldByBean(cls.getSuperclass(), list);
		}
	}
	
	/**
	 *  获取字段title，获取不到取字段的名称
	 * @param f
	 * @return
	 */
	public static String getTitleName(Field f) {
		String titleName = "";
		Title title = f.getAnnotation(Title.class);
		if(title == null) {
			titleName = f.getName();
		}else {
			titleName = title.value();
		}
		return titleName;
	}

}
