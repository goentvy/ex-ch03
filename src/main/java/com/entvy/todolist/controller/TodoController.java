package com.entvy.todolist.controller;

import com.entvy.todolist.model.Todo;
import com.entvy.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.getTodos());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) {
        todoService.addTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateTodo(@ModelAttribute Todo todo) {
        todoService.updateTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTodo(@RequestParam Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }
}
