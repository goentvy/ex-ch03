package com.entvy.todolist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.entvy.todolist.mapper")
@SpringBootApplication
public class TodoListApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }
}
