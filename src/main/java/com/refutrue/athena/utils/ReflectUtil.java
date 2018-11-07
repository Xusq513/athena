package com.refutrue.athena.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.refutrue.athena.utils.template.annotation.Calender;
import com.refutrue.athena.utils.template.annotation.Ignore;
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
	 * 将一个实体的列表转化为List<Map>,根据个性化，将返参个性化返回
	 * 
	 * @param cls
	 *            实体类型
	 * @param list
	 *            实体列表
	 * @return
	 */
	public static <T> List<Map<String, String>> beans2Map(Class<T> cls, List<T> list) {
		List<Map<String, String>> mapList = new ArrayList<>();
		list.forEach(t -> {
			Map<String, String> map = bean2StringMap(cls, t);
			mapList.add(map);
		});
		return mapList;
	}

	/**
	 *  将实体转化为Map，值经过特殊化处理
	 * @param cls 实体类型
	 * @param t 实体实例
	 * @return
	 */
	public static <T> Map<String, String> bean2StringMap(Class<T> cls, T t) {
		List<Field> fields = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		getAllFieldByBean(cls,fields);
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(t);
				String filedString = convertField(field, value);
				if(filedString != null) {
					map.put(field.getName(), filedString);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 将某个字段进行转化
	 * 
	 * @param f
	 *            字段类型
	 * @param o
	 *            字段值
	 * @return 转化后的字符串
	 */
	public static String convertField(Field f, Object o) {
		String returnStr = "";
		if (o != null) {
			// 如果是日期控件，需要进行日期转化
			Calender calender = f.getAnnotation(Calender.class);
			// 如果不不是数据库字段
			Ignore ignore = f.getAnnotation(Ignore.class);
			if (calender != null && StringUtil.isNotEmptyOrNull(calender.javaFormatter())
					&& f.getType() == Date.class) {
				returnStr = DateUtil.date2Str((Date) o, calender.javaFormatter());
			}else if(ignore != null){
				returnStr = null;
			}else {
				returnStr = o.toString();
			}
		}
		return returnStr;
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
