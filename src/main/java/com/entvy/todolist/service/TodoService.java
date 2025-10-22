package com.entvy.todolist.service;

import com.entvy.todolist.mapper.TodoMapper;
import com.entvy.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoMapper todoMapper;

    public List<Todo> getTodos() {
        return todoMapper.findAll();
    }

    public void addTodo(Todo todo) {
        todoMapper.insert(todo);
    }

    public void updateTodo(Todo todo) {
        todoMapper.update(todo);
    }

    public void deleteTodo(Long id) {
        todoMapper.delete(id);
    }
}
