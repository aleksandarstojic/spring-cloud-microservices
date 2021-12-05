package com.microservices.studentservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
	//@Autowired
	//private CourseProxy courseProxy;

	@GetMapping("students")
	public ResponseEntity getAllStudents() {
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("students/{id}")
	public ResponseEntity getStudent(@PathVariable long id) {
		try {
		Student student = studentService.getStudent(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
		}
		catch (StudentNotFoundException snfe) {
			String msg = snfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("students")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student newStudent = studentService.createStudent(student);
		return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
	}

	@DeleteMapping("students/{id}")
	public ResponseEntity removeStudent(@PathVariable long id) {
		try {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Student with the id " + id + " does not exsist.", HttpStatus.NOT_FOUND);
		}
	}
}