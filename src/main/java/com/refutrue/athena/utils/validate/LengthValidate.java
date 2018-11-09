package com.refutrue.athena.utils.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.ReflectUtil;
import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.template.annotation.Order;
import com.refutrue.athena.utils.template.annotation.Validate;

@Order(2)
@Component("lengthValidate")
public class LengthValidate extends ValidateAdapter{

	@Override
	public <T> void check(Class<T> cls, T t) throws AthenaException {
		List<Field> fieldList = new ArrayList<>();
		ReflectUtil.getAllFieldByBean(cls, fieldList);
		fieldList.forEach(f ->{
			Validate validate = f.getAnnotation(Validate.class);
			if(validate != null && validate.length() != Integer.MAX_VALUE) {
				String titleName = getTitleName(f);
				Object o = null;
				try {
					f.setAccessible(true);
					o = f.get(t);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				// 主要针对字符串类型和日期类型的，其他类型的不进行限制
				// TODO 这个地方的length目前是字符的个数，到时候要确定汉字是计算几个长度
				if(o != null && f.getType() == String.class && StringUtil.obj2Str(o).length() > validate.length()) {
					throw new AthenaException("提交的数据中字段【" + titleName + "】超出最大长度" + validate.length());
				}
			}
		});
	}

}
