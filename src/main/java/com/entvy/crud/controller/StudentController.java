package com.entvy.crud.controller;

import com.entvy.crud.domain.Student;
import com.entvy.crud.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> list() {
        return studentService.findAll();
    }
}
