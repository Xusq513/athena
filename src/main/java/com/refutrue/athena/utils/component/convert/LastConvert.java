package com.refutrue.athena.utils.component.convert;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.template.annotation.Order;

@Order(1000)
@Component
public class LastConvert implements IConvert{

	@Override
	public String convert(Field f, Object o) throws AthenaException {
		return o.toString();
	}

}
