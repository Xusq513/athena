package com.refutrue.athena.utils.component.convert;

import java.lang.reflect.Field;

import com.refutrue.athena.utils.template.annotation.Ignore;
import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.template.annotation.Order;

@Order(100)
@Component
public class IgnoreConvert implements IConvert{

	@Override
	public String convert(Field f, Object o) throws AthenaException {
		Ignore ignore = f.getAnnotation(Ignore.class);
		if(ignore != null){
			return null;
		}
		return o.toString();
	}

}
