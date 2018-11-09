package com.refutrue.athena.utils.template.builder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.ApplicationContextProvider;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/6 16:26
 * @Description:
 */
@Component
public class BuilderFactory {

	@Autowired
	private ApplicationContextProvider context;

	public void execute(Class<?> cls) {
		List<IBuilder> builderList = context.getBeanListByOrder(IBuilder.class);
		builderList.forEach(builder -> {
			builder.execute(cls);
		});
	}
}
