package com.refutrue.athena.utils.template.builder;

import org.apache.velocity.VelocityContext;

import com.refutrue.athena.bean.User;
import com.refutrue.athena.utils.template.bean.BeanMsg;
import com.refutrue.athena.utils.template.bean.GlobalConfig;
import com.refutrue.athena.utils.template.exception.TemplateException;
import com.refutrue.athena.utils.template.generate.GenerateFactory;
import com.refutrue.athena.utils.template.velocity.VelocityUtil;

public class MapperBuilder extends BuilderAdapter{

	@Override
	public void execute(Class<?> cls) {
		BeanMsg beanMsg = collectBeanMsg(cls);
		GlobalConfig globalConfig = GlobalConfig.getInstance().init();
		VelocityContext ctx = new VelocityContext();
		ctx.put("beanMsg", beanMsg);
		ctx.put("globalConfig", globalConfig);
		String templateName = "Mapper.vm";
		String velocityStr = VelocityUtil.zeus(templateName, ctx);
		String path = getProjectFilePath(globalConfig.getBasePackage(),globalConfig.getGenerateMapperDir(),beanMsg.getBeanSimpleName() + "Mapper" + ".java");
		GenerateFactory.getInstance().build(globalConfig.getGenerateMethod()).generate(path, new StringBuilder(velocityStr));
	}
	
	public static void main(String[] args) {
		MapperBuilder builder = new MapperBuilder();
		builder.execute(User.class);
	}

	@Override
	public void check(Class<?> cls) throws TemplateException {
		
	}

}
