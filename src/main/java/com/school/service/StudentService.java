package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.Course;
import com.school.entity.Student;

import com.school.repository.StudentRepository;
import com.school.repository.CourseRepository;
import com.school.repository.TeacherRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void removeStudent(Long studentId) {
       studentRepository.deleteById(studentId);
    }

    public List<Course> getCoursesEnrolledByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return (student != null) ? student.getEnrolledCourses() : null;
    }

    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            student.enrollInCourse(course);
            studentRepository.save(student);
        }
    }

    public void withdrawStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            student.withdrawFromCourse(course);
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
    
    public Student updateStudent(Long studentId, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent != null) {
            existingStudent.setStudentName(updatedStudent.getStudentName());
             return studentRepository.save(existingStudent);
        } else {
            return null; 
        }
    }
}

