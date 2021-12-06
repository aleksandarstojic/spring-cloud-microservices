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

@RestController
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
		catch (RuntimeException snfe) {
			String msg = snfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("faculties")
	public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
		Faculty newFaculty = facultyService.createFaculty(faculty);
		return new ResponseEntity<>(newFaculty, HttpStatus.CREATED);
	}

	@DeleteMapping("faculties/{id}")
	public ResponseEntity removeFaculty(@PathVariable long id) {
		try {
			facultyService.removeFaculty(id);
		return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Faculty with the id " + id + " does not exsist.", HttpStatus.NOT_FOUND);
		}
	}
}
