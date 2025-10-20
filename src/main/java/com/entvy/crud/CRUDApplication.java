package com.entvy.crud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
@MapperScan("com.entvy.crud.mapper")
public class CRUDApplication {

    public static void main(String[] args) {
        SpringApplication.run(CRUDApplication.class, args);
    }

}
