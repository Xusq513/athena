package com.refutrue.athena.utils.template.generate;

import com.refutrue.athena.utils.template.exception.TemplateException;

public class GenerateFactory {
	
	public static GenerateFactory factory = new GenerateFactory();
	
	private IGenerate local ;
	
	private IGenerate resource ;
	
	private IGenerate project ;
	
	private GenerateFactory() {
		
	}
	
	public static GenerateFactory getInstance() {
		return factory;
	}
	
	public IGenerate build(String type) throws TemplateException{
		switch (type) {
		case "Project":
			if(project == null) {
				project = new GenerateProjectFile();
			}
			return project;
		case "Resource":
			if(resource == null) {
				resource = new GenerateResourceFile();
			}
			return resource;
		case "Local":
			if(local == null) {
				local = new GenerateLocalFile();
			}
			return local;
		default:
			throw new TemplateException("传入的类型不支持！");
		}
	}

}
