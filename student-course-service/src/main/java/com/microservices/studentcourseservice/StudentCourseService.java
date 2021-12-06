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

	public List<StudentCourse> getAllStudentsCourses() {
		return studentCourseRepository.findAll();
	}
	
	public List<StudentCourse> getCoursesByStudent(long studentId) {
		List<StudentCourse> studentCourses = studentCourseRepository.findByStudentId(studentId);
		if(studentCourses.isEmpty()) {
			throw new StudentCourseNotFoundException("Student with id " + studentId + " is not signed up for any courses.");
		}
		return studentCourses;
	}

	public List<StudentCourse> getStudentsByCourse(long courseId) {
		List<StudentCourse> courseStudents = studentCourseRepository.findByCourseId(courseId);
		if(courseStudents.isEmpty()) {
			throw new StudentCourseNotFoundException("Course with id " + courseId + " does not have any students signed up for it.");
		}
		return courseStudents;
	}

	public StudentCourse addStudentCourse(long studentId, long courseId) {

//		ResponseEntity<StudentDTO> studentResponseEntity = restTemplate
//                .getForEntity("http://student-service/students/{id}", StudentDTO.class, studentId);
		ResponseEntity<StudentDTO> studentResponseEntity = restTemplate
				.getForEntity("http://localhost:8000/students/{id}", StudentDTO.class, studentId);
		if (studentResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new EntityNotFoundException("Student with the given id does not exist.");
		}

//        ResponseEntity<CourseDTO> courseResponseEntity = restTemplate
//                .getForEntity("http://course-service/students/{id}", CourseDTO.class, courseId);
		ResponseEntity<CourseDTO> courseResponseEntity = restTemplate
				.getForEntity("http://localhost:8100/courses/{id}", CourseDTO.class, courseId);

		if (courseResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new EntityNotFoundException("Course with the given id does not exist.");
		}

		Optional<StudentCourse> entity = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);

		if (entity.isPresent()) {
			throw new StudentCourseAlreadyExistsException("Student-course entity already exists.");
		}

		return studentCourseRepository.save(new StudentCourse(studentId, courseId));
	}

	public void deleteStudentCourse(long studentId, long courseId) {
		Optional<StudentCourse> entity = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
		if (entity.isEmpty()) {
            throw new StudentCourseNotFoundException("Student-Course with student id " + studentId + " and course id " + courseId + " does not exist.");
        } else {
        	studentCourseRepository.delete(entity.get());
        }
	}
}
