package com.refutrue.athena.utils.component.convert;

import java.lang.reflect.Field;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.DateUtil;
import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.component.annotation.Calender;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.template.annotation.Order;

@Order(6)
@Component
public class CalenderConvert implements IConvert{

	@Override
	public String convert(Field f, Object o) throws AthenaException {
		String returnStr ="";
		// 如果是日期控件，需要进行日期转化
		Calender calender = f.getAnnotation(Calender.class);
		if (calender != null && StringUtil.isNotEmptyOrNull(calender.javaFormatter())
				&& f.getType() == Date.class) {
			returnStr = DateUtil.date2Str((Date) o, calender.javaFormatter());
		}
		return returnStr;
	}

}
