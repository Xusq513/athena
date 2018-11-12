package com.refutrue.athena.utils.component.convert;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.template.annotation.Order;

@Order(100)
@Component
public class IgnoreConvert implements IConvert{

	@Override
	public String convert(Field f, Object o) throws AthenaException {
		// 不要质疑，这里的的确确就是要返回一个null
		return null;
	}

}
