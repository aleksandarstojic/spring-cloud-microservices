package com.microservices.studentcourseservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="student-service")
public interface StudentProxy {
	
	@GetMapping("students/{id}")
	public StudentDTO getStudent(@PathVariable long id);
}
