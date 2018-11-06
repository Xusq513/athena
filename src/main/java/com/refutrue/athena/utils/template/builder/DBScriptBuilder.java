package com.refutrue.athena.utils.template.builder;

import org.apache.velocity.VelocityContext;

import com.refutrue.athena.bean.User;
import com.refutrue.athena.utils.template.bean.GlobalConfig;
import com.refutrue.athena.utils.template.bean.TableMsg;
import com.refutrue.athena.utils.template.exception.TemplateException;
import com.refutrue.athena.utils.template.generate.GenerateFactory;
import com.refutrue.athena.utils.template.velocity.VelocityUtil;

public class DBScriptBuilder extends BuilderAdapter{

	@Override
	public void execute(Class<?> cls) throws TemplateException{
		TableMsg tableMsg = collectionTableMsg(cls);
		VelocityContext ctx = new VelocityContext();
		ctx.put("tableMsg", tableMsg);
		String templateName = "DB_" +  tableMsg.getDbType() + ".vm";
		String velocityStr = VelocityUtil.zeus(templateName, ctx);
		GlobalConfig globalConfig = GlobalConfig.getInstance().init();
		String path = getProjectFilePath(globalConfig.getBasePackage(),"sql",tableMsg.getTableName() + ".sql");
		GenerateFactory.getInstance().build(globalConfig.getGenerateMethod()).generate(path, new StringBuilder(velocityStr));
	}
	
	public static void main(String[] args) {
		DBScriptBuilder builder = new DBScriptBuilder();
		builder.execute(User.class);
	}
	
	

	@Override
	public void check(Class<?> cls) throws TemplateException {
		
	}

}
