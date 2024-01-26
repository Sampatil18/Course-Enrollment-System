package com.school.controller;

import com.school.entity.Teacher;
import com.school.service.StudentService;
import com.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Autowired
    public StudentService studentService;

    @GetMapping("/allteacher")
    public String getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return "List of Teachers: " + teachers;
    }

    @GetMapping("/{teacherId}")
    public String getTeacherById(@PathVariable Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return "Teacher Details: " + teacher;
    }

    @PostMapping("/addteacher")
    public String createTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.createTeacher(teacher);
        return "Teacher Created: " + createdTeacher;
    }

    @PutMapping("/{teacherId}")
    public String updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher updatedTeacher) {
        Teacher teacher = teacherService.updateTeacher(teacherId, updatedTeacher);
        return "Teacher Updated: " + teacher;
    }

    @DeleteMapping("/{teacherId}")
    public String deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return "Teacher Deleted";
    }

    @PostMapping("/{teacherId}/assign-course/{courseId}")
    public String assignTeacherToCourse(@PathVariable Long teacherId, @PathVariable Long courseId) {
        teacherService.assignTeacherToCourse(teacherId, courseId);
        return "Teacher assigned to Course";
    }

    @PostMapping("/admin/{studentId}/enroll/{courseId}")
    public String enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.enrollStudentInCourse(studentId, courseId);
        return "Student enrolled in Course";
    }

    @PostMapping("/admin/{studentId}/withdraw/{courseId}")
    public String withdrawStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.withdrawStudentFromCourse(studentId, courseId);
        return "Student withdrawn from Course";
    }
}
