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

	@GetMapping("students")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("students/{id}")
	public Optional<Student> getStudent(@PathVariable long id) {
		return studentService.getStudent(id);
	}

	@PostMapping("students")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student newStudent = studentService.createStudent(student);
		return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
	}

	@DeleteMapping("students/{id}")
	public ResponseEntity<Student> removeStudent(@PathVariable long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
