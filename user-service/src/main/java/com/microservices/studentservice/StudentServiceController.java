package com.microservices.studentservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentServiceController {

	@GetMapping("students")
	public List<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		Student student1 = new Student("Aleksandar", "Stojic", 1);
		Student student2 = new Student("Nikola", "Savic", 2);
		students.add(student1);
		students.add(student2);
		return students ;
	}
	
	@GetMapping("students/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student1 = new Student("Aleksandar", "Stojic", 1);
		return student1;
	}
	
	@PostMapping("students")
	public ResponseEntity<Student> addStudent() {
		return new ResponseEntity<Student>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("students/{id}")
	public ResponseEntity<Student> removeStudent() {
		return new ResponseEntity<Student>(HttpStatus.OK);
	}
}
