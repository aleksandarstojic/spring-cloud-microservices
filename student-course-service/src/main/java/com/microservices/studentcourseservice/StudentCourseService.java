package com.microservices.studentcourseservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {
	
	@Autowired
	StudentCourseRepository studentCourseRepository;

	public List<StudentCourse> getCoursesByStudent(long studentId) {
		return studentCourseRepository.findByStudentId(studentId);
	}
	
	public List<StudentCourse> getStudentsByCourse(long courseId) {
		return studentCourseRepository.findByCourseId(courseId);
	}

}
