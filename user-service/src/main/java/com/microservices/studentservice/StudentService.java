package com.microservices.studentservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Optional<Student> getStudent(long id) {
		return studentRepository.findById(id);
	}
	
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public void deleteStudent(long id) {
		studentRepository.deleteById(id);
	}
}
