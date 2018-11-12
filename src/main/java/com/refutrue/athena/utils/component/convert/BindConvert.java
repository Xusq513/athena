package com.refutrue.athena.utils.component.convert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.ApplicationContextProvider;
import com.refutrue.athena.utils.StringUtil;

@Component
public class BindConvert {
	
	@Autowired
	private ApplicationContextProvider context;

	/**
	 * 将实体转化为Map，值经过特殊化处理
	 * 
	 * @param cls
	 *            实体类型
	 * @param t
	 *            实体实例
	 * @return
	 */
	public <T> Map<String, String> convert(Class<T> cls, T t) {
		List<Field> fields = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		getAllFieldByBean(cls, fields);
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(t);
				String filedString = convertField(field, value);
				if (filedString != null) {
					map.put(field.getName(), filedString);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
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
	public <T> List<Map<String, String>> convertList(Class<T> cls, List<T> list) {
		List<Map<String, String>> mapList = new ArrayList<>();
		list.forEach(t -> {
			Map<String, String> map = convert(cls, t);
			mapList.add(map);
		});
		return mapList;
	}

	/**
	 * 
	 * @param f
	 *            字段类型
	 * @param o
	 *            字段值
	 * @return 转化后的字符串
	 */
	private String convertField(Field f, Object o) {
		String returnStr = "";
		List<IConvert> convertInterfaces = context.getBeanListByOrder(IConvert.class);
		if (o != null) {
			for(IConvert convert : convertInterfaces) {
				returnStr = convert.convert(f, o);
				if(returnStr == null || StringUtil.isNotEmptyOrNull(returnStr)) {
					break;
				}
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
	private void getAllFieldByBean(Class<?> cls, List<Field> list) {
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

}
