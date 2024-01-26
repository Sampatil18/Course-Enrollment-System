package com.school.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;
    private int maxStudents;

   
    @ManyToMany(mappedBy = "enrolledCourses")
    private List<Student> enrolledStudents;

  
    @ManyToOne
    private Teacher teacher;

    public Course() {
        
    }

    public Course(String courseName, int maxStudents) {
        this.courseName = courseName;
        this.maxStudents = maxStudents;
        enrolledStudents = new ArrayList<>();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Student student) {
        if (enrolledStudents.size() < maxStudents) {
            enrolledStudents.add(student);
            student.enrollInCourse(this);
        } else {
            throw new RuntimeException("Course is full. Cannot enroll more students.");
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
        student.withdrawFromCourse(this);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", maxStudents=" + maxStudents +
                ", teacher=" + (teacher != null ? teacher.getTeacherName() : "N/A") +
                '}';
    }
}
