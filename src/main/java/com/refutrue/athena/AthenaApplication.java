package com.refutrue.athena;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.refutrue.athena.mapper")
public class AthenaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AthenaApplication.class, args);
    }
}
