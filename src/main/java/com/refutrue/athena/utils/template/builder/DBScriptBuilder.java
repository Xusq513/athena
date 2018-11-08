package com.refutrue.athena.utils.template.builder;

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.template.bean.GlobalConfig;
import com.refutrue.athena.utils.template.bean.TableMsg;
import com.refutrue.athena.utils.template.exception.TemplateException;
import com.refutrue.athena.utils.template.generate.GenerateFactory;
import com.refutrue.athena.utils.template.velocity.VelocityUtil;

@Component(value="dbScriptBuilder")
public class DBScriptBuilder extends BuilderAdapter{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public void execute(Class<?> cls) throws TemplateException{
		TableMsg tableMsg = collectTableMsg(cls);
		VelocityContext ctx = new VelocityContext();
		ctx.put("tableMsg", tableMsg);
		String templateName = "DB_" +  tableMsg.getDbType() + ".vm";
		String velocityStr = VelocityUtil.zeus(templateName, ctx);
		GlobalConfig globalConfig = GlobalConfig.getInstance().init();
		String path = getProjectFilePath(globalConfig.getBasePackage(),"sql",tableMsg.getTableName() + ".sql");
		GenerateFactory.getInstance().build(globalConfig.getGenerateMethod()).generate(path, new StringBuilder(velocityStr));
		String[] sqls = velocityStr.split(";");
		for(String sql : sqls) {
			if(StringUtil.isNotEmptyOrNull(sql.trim())) {
				jdbcTemplate.execute(sql);
			}
		}
	}
	

	@Override
	public void check(Class<?> cls) throws TemplateException {
		
	}

}
