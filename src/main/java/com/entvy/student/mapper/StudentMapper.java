package com.entvy.student.mapper;

import com.entvy.student.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    // 모든 학생 조회
    List<Student> findAll();
    // 특정 학생 조회
    Student findById(Long id);
    // 학생 추가
    void insert(Student student);
    // 학생 정보 수정
    void update(Student student);
    // 학생 제거
    void delete(Long id);
    // Email 중복 확인
    boolean existsByEmail(String email);
}
