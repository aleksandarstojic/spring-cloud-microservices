package com.microservices.departmentservice;

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

@RestController
@CircuitBreaker(name="default", fallbackMethod="fallback")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

	@GetMapping("departments")
	public ResponseEntity getAllDepartments() {
		return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
	}

	@GetMapping("departments/{id}")
	public ResponseEntity getDepartment(@PathVariable long id) {
		try {
			Department department = departmentService.getDepartment(id);
			return new ResponseEntity<Department>(department, HttpStatus.OK);
		} catch (DepartmentNotFoundException dnfe) {
			String msg = dnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("departments")
	public ResponseEntity createDepartment(@RequestBody Department department) {

		try {
			Department newDepartment = departmentService.createDepartment(department);
			return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
		} catch (RuntimeException re) {
			String msg = re.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("departments/{id}")
	public ResponseEntity removeDepartment(@PathVariable long id) {
		try {
			departmentService.removeDepartment(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (DepartmentNotFoundException dnfe) {
			String msg = dnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity fallback(RuntimeException e) {
	    return new ResponseEntity<String>("Department service is taking too long to respond. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
	}
}
