package com.refutrue.athena.utils.template.generate;

import java.io.File;
import java.io.IOException;

import com.refutrue.athena.utils.template.exception.TemplateException;

public class GenerateResourceFile extends GenerateAdapter{
	
	public static final String basePath = "src/main/resources/";

	@Override
	public void generate(String path, StringBuilder sb) throws TemplateException {
		path = basePath + path;
		File file = new File(path);
		try {
			generateFile(file, sb);
		} catch (IOException e) {
			e.printStackTrace();
			throw new TemplateException("生成【" + path + "】失败！");
		}
	}
	

}
