package com.refutrue.athena.utils.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.refutrue.athena.utils.ReflectUtil;
import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.template.annotation.Validate;

public class RequiredValidate extends ValidateAdapter{

	@Override
	public <T> void check(Class<T> cls, T t) throws AthenaException {
		List<Field> fieldList = new ArrayList<>();
		ReflectUtil.getAllFieldByBean(cls, fieldList);
		fieldList.forEach(f ->{
			Validate validate = f.getAnnotation(Validate.class);
			String titleName = getTitleName(f);
			Object o = null;
			try {
				f.setAccessible(true);
				o = f.get(t);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			if(validate != null && validate.required()) {
				// 主要针对字符串类型和日期类型的，其他类型的不进行限制
				if(f.getType() == String.class && StringUtil.isEmptyOrNull(o)) {
					throw new AthenaException("提交的数据中字段【" + titleName + "】是必填项");
				}
			}
			
		});
	}

}
