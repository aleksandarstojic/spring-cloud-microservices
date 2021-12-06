package com.microservices.studentservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Student getStudent(long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isEmpty()) {
			throw new StudentNotFoundException("Student with the id " + id + " does not exsist.");
		}
		return student.get();
	}
	
	public Student createStudent(Student student) {
		Optional<Student> existingStudent = studentRepository.findById(student.getId());
		if(existingStudent.isPresent()) {
			throw new StudentAlreadyExistsException("Student with the id " + student.getId() + " already exists.");
		}
		return studentRepository.save(student);
	}
	
	public void deleteStudent(long id) {
		Optional<Student> existingStudent = studentRepository.findById(id);
		if(existingStudent.isEmpty()) {
			throw new StudentNotFoundException("Student with the id " + id + " does not exsist.");
		}
		studentRepository.deleteById(id);
	}
}
