package com.school.controller;

import com.school.entity.Student;
import com.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/allstudents")
    public String getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return "All students: " + students.toString();
    }

    @GetMapping("/{studentId}")
    public String getStudentById(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return (student != null) ? student.toString() : "Student not found";
    }

    @PostMapping("/addstudent")
    public String createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return "Student created: ";
    }

    @PutMapping("/{studentId}")
    public String updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        Student student = studentService.updateStudent(studentId, updatedStudent);
        return (student != null) ? "Student updated: " : "Student not found";
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return "Student deleted";
    }
}
