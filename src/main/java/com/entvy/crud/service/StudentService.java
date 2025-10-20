package com.entvy.crud.service;

import com.entvy.crud.domain.Student;
import com.entvy.crud.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentMapper studentMapper;

    public List<Student> findAll() {
        return studentMapper.findAll();
    }
}
