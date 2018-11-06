package com.refutrue.athena.utils.template.generate;

import com.refutrue.athena.utils.template.exception.TemplateException;

public interface IGenerate {
	
	public void generate(String path,StringBuilder sb) throws TemplateException;

}
