package com.refutrue.athena;

import com.refutrue.athena.bean.User;
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
    
//    @Autowired
//    private RedisUtil redisUtil;
    
//    @Test
//    public void contextLoads() {
//        builderFactory.execute(User.class);
//    }
    
//    @Test
//    public void testCache() {
//    	Dic dic1 = new Dic();
//    	dic1.setCode("22");
//    	Dic dic2 = new Dic();
//    	dic2.setCode("23");
//    	List<Dic> list = new ArrayList<>();
//    	list.add(dic1);
//    	list.add(dic2);
//    	redisUtil.hset("haha", "ee", list);
//    	List<Dic> listd = (List<Dic>) redisUtil.hget("haha", "1");
//    	System.out.println(listd);
//    }

}
