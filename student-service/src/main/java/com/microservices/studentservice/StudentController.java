package com.microservices.studentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@CircuitBreaker(name = "default", fallbackMethod = "fallback")
@RateLimiter(name = "default")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("students")
	public ResponseEntity getAllStudents() {
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("students/{id}")
	public ResponseEntity getStudent(@PathVariable long id) {
		try {
			Student student = studentService.getStudent(id);
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		} catch (StudentNotFoundException snfe) {
			String msg = snfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("students")
	public ResponseEntity createStudent(@RequestBody Student student) {
		try {
			Student newStudent = studentService.createStudent(student);
			return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
		} catch (StudentAlreadyExistsException saee) {
			String msg = saee.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("students/{id}")
	public ResponseEntity removeStudent(@PathVariable long id) {
		try {
			studentService.deleteStudent(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (StudentNotFoundException snfe) {
			String msg = snfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity fallback(RuntimeException e) {
		return new ResponseEntity<String>("Student service is taking too long to respond. Please try again later.",
				HttpStatus.SERVICE_UNAVAILABLE);
	}
}
