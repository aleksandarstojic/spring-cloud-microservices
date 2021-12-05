package com.microservices.studentcourseservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentCourseService {

	@Autowired
	StudentCourseRepository studentCourseRepository;

	@Autowired
	private RestTemplate restTemplate = this.restTemplate();

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public List<StudentCourse> getCoursesByStudent(long studentId) {
		return studentCourseRepository.findByStudentId(studentId);
	}

	public List<StudentCourse> getStudentsByCourse(long courseId) {
		return studentCourseRepository.findByCourseId(courseId);
	}

	public StudentCourse addStudentCourse(long studentId, long courseId) {

//		ResponseEntity<StudentDTO> studentResponseEntity = restTemplate
//                .getForEntity("http://student-service/students/{id}", StudentDTO.class, studentId);
		ResponseEntity<StudentDTO> studentResponseEntity = restTemplate
				.getForEntity("http://localhost:8000/students/{id}", StudentDTO.class, studentId);
		if (studentResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new RuntimeException("Student with the given id does not exists");
		}

//        ResponseEntity<CourseDTO> courseResponseEntity = restTemplate
//                .getForEntity("http://course-service/students/{id}", CourseDTO.class, courseId);
		ResponseEntity<CourseDTO> courseResponseEntity = restTemplate
				.getForEntity("http://localhost:8100/courses/{id}", CourseDTO.class, courseId);

		if (courseResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new RuntimeException("Course with the given id does not exists");
		}

		Optional<StudentCourse> entity = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);

		if (entity.isPresent()) {
			throw new RuntimeException("Student-course entity already exists");
		}

		return studentCourseRepository.save(new StudentCourse(studentId, courseId));
	}

}
