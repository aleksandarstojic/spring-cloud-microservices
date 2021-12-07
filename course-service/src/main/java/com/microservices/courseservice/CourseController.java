package com.microservices.courseservice;

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
@RateLimiter(name="default")
public class CourseController {

	@Autowired
	CourseService courseService;

	@GetMapping("courses")
	public ResponseEntity getAllCourses() {
		return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
	}

	@GetMapping("courses/{id}")
	public ResponseEntity getCourse(@PathVariable long id) {
		try {
			Course course = courseService.getCourse(id);
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		} catch (CourseNotFoundException cnfe) {
			String msg = cnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("courses")
	public ResponseEntity createCourse(@RequestBody Course course) {
		try {
			Course newCourse = courseService.createCourse(course);
			return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
		} catch (RuntimeException re) {
			String msg = re.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("courses/{id}")
	public ResponseEntity removeStudent(@PathVariable long id) {
		try {
			courseService.deleteCourse(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CourseNotFoundException cnfe) {
			String msg = cnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity fallback(RuntimeException e) {
	    return new ResponseEntity<String>("Course service is taking too long to respond. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
	}
}
