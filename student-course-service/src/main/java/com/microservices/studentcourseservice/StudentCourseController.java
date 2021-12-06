package com.microservices.studentcourseservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StudentCourseController {

	@Autowired
    StudentCourseService studentCourseService;
    
	@GetMapping("/students-courses")
    public ResponseEntity getAllStudentsCourses() { 
        return new ResponseEntity<>(studentCourseService.getAllStudentsCourses(), HttpStatus.OK);
    }
	
    @GetMapping("/courses-by-student/{id}")
    public ResponseEntity getCoursesByStudent(@PathVariable long id) { 
    	try {
			List<StudentCourse> courseStudents = studentCourseService.getCoursesByStudent(id);
			return new ResponseEntity<>(courseStudents, HttpStatus.OK);
		} catch (StudentCourseNotFoundException scnfe) {
			String msg = scnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}     
    }
    
    @GetMapping("/students-by-course/{id}")
    public ResponseEntity getStudentsByCourse(@PathVariable long id) {
        try {
			List<StudentCourse> studentCourses = studentCourseService.getStudentsByCourse(id);
			return new ResponseEntity<>(studentCourses, HttpStatus.OK);
		} catch (StudentCourseNotFoundException scnfe) {
			String msg = scnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
    }
    
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity addNewStudentCourse(@PathVariable long studentId, @PathVariable long courseId) {
    	try {
			StudentCourse newStudentCourse = studentCourseService.addStudentCourse(studentId, courseId);
			return new ResponseEntity<>(newStudentCourse, HttpStatus.CREATED);
		} catch (RuntimeException re) {
			String msg = re.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
    }
    
    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity deleteStudentCourse(@PathVariable long studentId, @PathVariable long courseId) {
        try {
        	studentCourseService.deleteStudentCourse(studentId, courseId);
            return new ResponseEntity<>(HttpStatus.OK);
		} catch (StudentCourseNotFoundException scnfe) {
			String msg = scnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
    }
}
