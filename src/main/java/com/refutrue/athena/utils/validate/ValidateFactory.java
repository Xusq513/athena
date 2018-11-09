package com.refutrue.athena.utils.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.ApplicationContextProvider;
import com.refutrue.athena.utils.exception.AthenaException;

@Component
public class ValidateFactory {
	
	@Autowired
	private ApplicationContextProvider context;

	
	public <T> void valid(Class<T> cls,T t) throws AthenaException{
		List<IValidate> list = context.getBeanListByOrder(IValidate.class);
		list.forEach(validate -> {
			validate.check(cls, t);
		});
	}
}
