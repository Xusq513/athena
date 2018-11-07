package com.refutrue.athena;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.refutrue.athena.bean.Student;
import com.refutrue.athena.utils.template.builder.BuilderFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.refutrue.athena.mapper")
public class AthenaApplicationTests {

    @Autowired
    private BuilderFactory builderFactory;
    
    @Test
    public void contextLoads() {
        builderFactory.execute(Student.class);
    }

}
