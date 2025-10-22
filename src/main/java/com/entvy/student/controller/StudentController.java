package com.entvy.student.controller;

import com.entvy.student.model.Student;
import com.entvy.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

//    @Operation(summary = "모든 학생 목록 조회", description = "등록된 모든 학생 정보를 반환합니다.")
//    @ApiResponse(responseCode = "200", description = "성공")
//    @GetMapping
//    public List<Student> getAll() {
//        return studentService.getAllStudents();
//    }

//  http://localhost:8080/students
//  학생 전체 조회
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/list";
    }
    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }
//  http://localhost:8080/students
//  학생 추가
    @PostMapping
    public String create(@ModelAttribute Student student) {
        studentService.createStudent(student);
        return "redirect:/students";
    }
//  http://localhost:8080/students/1/edit
//  수정폼
    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        return "student/form";
    }
//  http://localhost:8080/students/1
//  수정처리
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Student student) {
        student.setId(id);
        studentService.updateStudent(student);
        return "redirect:/students";
    }
//  http://localhost:8080/students/1/delete
//  삭제처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

//    @Operation(summary = "학생 단건 조회", description = "ID로 학생 정보를 조회합니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "성공"),
//            @ApiResponse(responseCode = "404", description = "학생을 찾을 수 없음",
//                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
//    })
//    @GetMapping("/{id}")
//    public Student getById(@PathVariable Long id) {
//        return studentService.getStudent(id);
//    }

//    @Operation(summary = "학생 등록", description = "새로운 학생 정보를 등록합니다.")
//    @ApiResponse(responseCode = "201", description = "등록 성공")
//    @PostMapping
//    public void create(@RequestBody @Valid Student student) {
//        studentService.createStudent(student);
//    }

//    @Operation(summary = "학생 정보 수정", description = "기존 학생 정보를 수정합니다.")
//    @ApiResponse(responseCode = "200", description = "수정 성공")
//    @PutMapping("/{id}")
//    public void update(@PathVariable Long id, @RequestBody @Valid Student student) {
//        student.setId(id);
//        studentService.updateStudent(student);
//    }

//    @Operation(summary = "학생 삭제", description = "ID로 학생 정보를 삭제합니다.")
//    @ApiResponse(responseCode = "204", description = "삭제 성공")
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        studentService.deleteStudent(id);
//    }
}
