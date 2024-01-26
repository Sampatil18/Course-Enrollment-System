package com.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.Course;
import com.school.entity.Teacher;
import com.school.repository.CourseRepository;
import com.school.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElse(null);
    }

    public Teacher createTeacher(Teacher teacher) {
       return teacherRepository.save(teacher);
    }

    public void removeTeacher(Long teacherId) {
         teacherRepository.deleteById(teacherId);
    }

    public List<Course> getCoursesTaughtByTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        return (teacher != null) ? teacher.getCoursesTaught() : null;
    }

    public void assignTeacherToCourse(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (teacher != null && course != null) {
            teacher.assignCourse(course);
            teacherRepository.save(teacher);
        }
    }

    public void unassignTeacherFromCourse(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (teacher != null && course != null) {
            teacher.unassignFromCourse(course);
            teacherRepository.save(teacher);
        }
    }

    public Teacher updateTeacher(Long teacherId, Teacher updatedTeacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setTeacherName(updatedTeacher.getTeacherName());
            return teacherRepository.save(teacher);
        } else {
            return null; 
        }
    }

    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

}