package com.refutrue.athena.utils.template.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/6 16:26
 * @Description:
 */
@Component
public class BuilderFactory {

	private List<IBuilder> builderList = new ArrayList<>();

	@Autowired
	@Qualifier(value = "resourceMapperBuilder")
	private IBuilder resourceMapperBuilder;

	@Autowired
	@Qualifier(value = "mapperBuilder")
	private IBuilder mapperBuilder;

	@Autowired
	@Qualifier(value = "serviceBuilder")
	private IBuilder serviceBuilder;

	@Autowired
	@Qualifier(value = "controllerBuilder")
	private IBuilder controllerBuilder;

	@Autowired
	@Qualifier(value = "dbScriptBuilder")
	private IBuilder dbScriptBuilder;

	public void execute(Class<?> cls) {
		builderList.add(resourceMapperBuilder);
		builderList.add(mapperBuilder);
		builderList.add(serviceBuilder);
		builderList.add(controllerBuilder);
		builderList.add(dbScriptBuilder);
		builderList.forEach(builder -> {
			builder.execute(cls);
		});
	}
}
