package com.microservices.studentcourseservice;

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
    
    @GetMapping("/courses-by-student/{id}")
    public ResponseEntity getCoursesByStudent(@PathVariable long id) { 
        return new ResponseEntity<>(studentCourseService.getCoursesByStudent(id), HttpStatus.OK);
    }
    
    @GetMapping("/students-by-course/{id}")
    public ResponseEntity getStudentsByCourse(@PathVariable long id) {
        return new ResponseEntity<>(studentCourseService.getStudentsByCourse(id), HttpStatus.OK);
    }
    
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity addNewStudentCourse(@PathVariable long studentId, @PathVariable long courseId) {
        return new ResponseEntity<>(studentCourseService.addStudentCourse(studentId, courseId), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity deleteStudentCourse(@PathVariable long studentId, @PathVariable long courseId) {
    	studentCourseService.deleteStudentCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
