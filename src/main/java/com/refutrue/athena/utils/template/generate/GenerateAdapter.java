package com.refutrue.athena.utils.template.generate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.refutrue.athena.utils.template.exception.TemplateException;

public abstract class GenerateAdapter implements IGenerate{
	
	public void generateFile(File file,StringBuilder sb) throws TemplateException, IOException{
		// TODO 目前的策略先删除，之后提供删除之前备份的策略
//		if(file.exists()) {
//			//long mills = new Date().getTime();
//			//Files.move(file.toPath(), file.toPath().resolveSibling(mills + "_" + file.getName()));
//			file.delete();
//		}
		Files.deleteIfExists(file.toPath());
		Files.createFile(file.toPath());
		Files.write(file.toPath(), sb.toString().getBytes());
	}
	
	

}
