package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.Course;
import com.school.entity.Student;
import com.school.entity.Teacher;
import com.school.repository.CourseRepository;
import com.school.repository.StudentRepository;
import com.school.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final TeacherRepository teacherRepository;
	private final StudentRepository studentRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository,
			StudentRepository studentRepository) {
		this.courseRepository = courseRepository;
		this.teacherRepository = teacherRepository;
		this.studentRepository = studentRepository;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourseById(Long courseId) {
		return courseRepository.findById(courseId).orElse(null);
	}

	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	public void removeCourse(Long courseId) {
		courseRepository.deleteById(courseId);
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

	public Course updateCourse(Long courseId, Course updatedCourse) {
		Optional<Course> optionalCourse = courseRepository.findById(courseId);

		if (optionalCourse.isPresent()) {
			Course existingCourse = optionalCourse.get();
			existingCourse.setCourseName(updatedCourse.getCourseName());
			existingCourse.setMaxStudents(updatedCourse.getMaxStudents());

			return courseRepository.save(existingCourse);
		}

		return null;

	}

	public void deleteCourse(Long courseId) {
		courseRepository.deleteById(courseId);
	}

}
