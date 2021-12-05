package com.microservices.studentservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient
public interface CourseProxy {
	
	@GetMapping("/courses")
	public Student getCourses();
}
