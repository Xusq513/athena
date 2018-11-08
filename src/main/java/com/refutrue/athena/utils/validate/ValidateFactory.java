package com.refutrue.athena.utils.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.exception.AthenaException;

@Component
public class ValidateFactory {

	@Autowired
	@Qualifier("dateValidate")
	private IValidate dateValidate;
	
	@Autowired
	@Qualifier("lengthValidate")
	private IValidate lengthValidate;
	
	
	@Autowired
	@Qualifier("requiredValidate")
	private IValidate requiredValidate;
	
	
	@Autowired
	@Qualifier("numberValidate")
	private IValidate numberValidate;
	
	
	public <T> void valid(Class<T> cls,T t) throws AthenaException{
		List<IValidate> list = new ArrayList<>();
		list.add(requiredValidate);
		list.add(lengthValidate);
		list.add(numberValidate);
		list.add(dateValidate);
		list.forEach(validate -> {
			validate.check(cls, t);
		});
	}
}
