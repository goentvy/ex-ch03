package com.entvy.todolist.mapper;

import com.entvy.todolist.model.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<Todo> findAll();

    void insert(Todo todo);

    void update(Todo todo);

    void delete(Long id);
}
