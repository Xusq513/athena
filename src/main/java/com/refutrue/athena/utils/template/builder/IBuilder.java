package com.refutrue.athena.utils.template.builder;

import com.refutrue.athena.utils.template.exception.TemplateException;

public interface IBuilder {

	public void execute(Class<?> cls);
	
	public void check(Class<?> cls) throws TemplateException;
}
