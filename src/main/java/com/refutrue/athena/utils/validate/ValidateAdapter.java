package com.refutrue.athena.utils.validate;

import java.lang.reflect.Field;

import com.refutrue.athena.utils.ReflectUtil;

public abstract class ValidateAdapter implements IValidate{

	protected String getTitleName(Field f) {
		return ReflectUtil.getTitleName(f);
	}
}
