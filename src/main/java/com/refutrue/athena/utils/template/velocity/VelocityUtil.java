package com.refutrue.athena.utils.template.velocity;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.refutrue.athena.utils.template.exception.TemplateException;

public class VelocityUtil {

	public static String zeus(String templateName, VelocityContext ctx) throws TemplateException{
		String returnStr = "";
		StringWriter sw = null;
		try {
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			ve.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
			ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
			ve.setProperty(Velocity.OUTPUT_ENCODING, "GBK");
			ve.init();
			Template t = ve.getTemplate("templates/" + templateName);
			sw = new StringWriter();
			t.merge(ctx, sw);
			returnStr = sw.toString();
		}catch (Exception e) {
			e.printStackTrace();
			throw new TemplateException("模板【" + templateName + "】采用velocity生成时报错！");
		}finally {
			if(sw != null) {
				try {
					sw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return returnStr;
		
	}
}
