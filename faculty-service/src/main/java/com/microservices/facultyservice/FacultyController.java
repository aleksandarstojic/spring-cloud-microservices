package com.microservices.facultyservice;

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
public class FacultyController {
	@Autowired
	FacultyService facultyService;

	@GetMapping("faculties")
	public ResponseEntity getAllFaculties() {
		return new ResponseEntity<>(facultyService.getAllFaculties(), HttpStatus.OK);
	}

	@GetMapping("faculties/{id}")
	public ResponseEntity getFaculty(@PathVariable long id) {
		try {
			Faculty faculty = facultyService.getFaculty(id);
		return new ResponseEntity<Faculty>(faculty, HttpStatus.OK);
		}
		catch (FacultyNotFoundException snfe) {
			String msg = snfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("faculties")
	public ResponseEntity createFaculty(@RequestBody Faculty faculty) {
		try {
		Faculty newFaculty = facultyService.createFaculty(faculty);
		return new ResponseEntity<>(newFaculty, HttpStatus.CREATED);
		} catch (FacultyAlreadyExistsException faee) {
			String msg = faee.getMessage();
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("faculties/{id}")
	public ResponseEntity removeFaculty(@PathVariable long id) {
		try {
			facultyService.removeFaculty(id);
		return new ResponseEntity<>(HttpStatus.OK);
		} catch (FacultyNotFoundException faee) {
			String msg = faee.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity fallback(RuntimeException e) {
	    return new ResponseEntity<String>("Faculty service is taking too long to respond. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
	}
}
