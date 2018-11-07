package com.refutrue.athena.utils.validate;

import com.refutrue.athena.utils.exception.AthenaException;

public interface IValidate {
	
	public <T> void check(Class<T> cls, T t) throws AthenaException;
}
