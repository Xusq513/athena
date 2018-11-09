package com.refutrue.athena.utils.template.builder;

import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.template.annotation.Order;
import com.refutrue.athena.utils.template.bean.BeanMsg;
import com.refutrue.athena.utils.template.bean.GlobalConfig;
import com.refutrue.athena.utils.template.bean.TableMsg;
import com.refutrue.athena.utils.template.exception.TemplateException;
import com.refutrue.athena.utils.template.generate.GenerateFactory;
import com.refutrue.athena.utils.template.velocity.VelocityUtil;

@Order(1)
@Component(value="resourceMapperBuilder")
public class ResourceMapperBuilder extends BuilderAdapter{

	@Override
	public void execute(Class<?> cls) {
		BeanMsg beanMsg = collectBeanMsg(cls);
		GlobalConfig globalConfig = GlobalConfig.getInstance().init();
		VelocityContext ctx = new VelocityContext();
		TableMsg tableMsg = collectTableMsg(cls);
		ctx.put("tableMsg", tableMsg);
		ctx.put("beanMsg", beanMsg);
		ctx.put("globalConfig", globalConfig);
		String templateName = "ResourceMapper.vm";
		String velocityStr = VelocityUtil.zeus(templateName, ctx);
		String path = getProjectFilePath("",globalConfig.getGenerateMapperDir(),beanMsg.getBeanSimpleName() + "Mapper" + ".xml");
		GenerateFactory.getInstance().build("Resource").generate(path, new StringBuilder(velocityStr));
	}
	
	@Override
	public void check(Class<?> cls) throws TemplateException {
		
	}

}
