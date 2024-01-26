package com.school.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String teacherName;

    @OneToMany(mappedBy = "teacher")
    private List<Course> coursesTaught;

    public Teacher() {
        coursesTaught = new ArrayList<>();
    }

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
        coursesTaught = new ArrayList<>();
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public void assignCourse(Course course) {
        coursesTaught.add(course);
        course.setTeacher(this);
    }
    
    public void unassignFromCourse(Course course) {
        coursesTaught.remove(course);
        course.setTeacher(null);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", coursesTaught=" + coursesTaught +
                '}';
    }
}

