package com.refutrue.athena;

import com.refutrue.athena.bean.User;
import com.refutrue.athena.utils.template.builder.BuilderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AthenaApplicationTests {

    @Autowired
    private BuilderFactory builderFactory;

    @Test
    public void contextLoads() {
        builderFactory.execute(User.class);
    }

}
