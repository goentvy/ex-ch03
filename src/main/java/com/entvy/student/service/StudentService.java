package com.entvy.student.service;

import com.entvy.student.error.DuplicateEmailException;
import com.entvy.student.error.StudentNotFoundException;
import com.entvy.student.mapper.StudentMapper;
import com.entvy.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAllStudents() {
        return studentMapper.findAll();
    }

    public Student getStudent(Long id) {
        Student student = studentMapper.findById(id);
        if(student == null) {
            throw new StudentNotFoundException(id);
        }
        return student;
    }

    public void createStudent(Student student) {
        if(studentMapper.existsByEmail(student.getEmail())) {
            throw new DuplicateEmailException(student.getEmail());
        }
        studentMapper.insert(student);
    }

    public void updateStudent(Student student) {
        studentMapper.update(student);
    }

    public void deleteStudent(Long id) {
        studentMapper.delete(id);
    }
}
