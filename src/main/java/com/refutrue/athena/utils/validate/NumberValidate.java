package com.refutrue.athena.utils.validate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.ReflectUtil;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.template.annotation.Validate;

@Component("numberValidate")
public class NumberValidate extends ValidateAdapter{

	@Override
	public <T> void check(Class<T> cls, T t) throws AthenaException {
		List<Field> fieldList = new ArrayList<>();
		ReflectUtil.getAllFieldByBean(cls, fieldList);
		fieldList.forEach(f ->{
			Validate validate = f.getAnnotation(Validate.class);
			
			if(validate != null) {
				String titleName = getTitleName(f);
				Object o = null;
				try {
					f.setAccessible(true);
					o = f.get(t);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				long minNum = validate.minNum();
				long maxNum = validate.maxNum();
				int weight = validate.weight();
				DecimalFormat df = new DecimalFormat("#.##");
				// 如果同时为默认值时不需要进行校验
				if(o != null && !(minNum == Long.MIN_VALUE && maxNum == Long.MAX_VALUE)) {
					BigDecimal num = null;
					try {
						num = new BigDecimal(o.toString());
					}catch (Exception e) {return;}
					num = num.multiply(new BigDecimal(weight));
					if(num.compareTo(new BigDecimal(minNum)) == -1 || num.compareTo(new BigDecimal(maxNum)) == 1) {
						throw new AthenaException("提交的数据中字段【" + titleName + "】不满足最小值【" + df.format(minNum/weight)  +"】，最大值【" + df.format(maxNum/weight) + "】");
					}
				}
			}
		});
	}

}
