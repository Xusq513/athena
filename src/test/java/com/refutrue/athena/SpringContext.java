package com.refutrue.athena;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.refutrue.athena.utils.ApplicationContextProvider;
import com.refutrue.athena.utils.template.builder.IBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.refutrue.athena.mapper")
public class SpringContext {
	
	@Autowired
	private ApplicationContextProvider context;
	
	@Test
	public void test() {
		System.out.println(context.getApplicationContext().getBeansOfType(IBuilder.class));
	}

}
