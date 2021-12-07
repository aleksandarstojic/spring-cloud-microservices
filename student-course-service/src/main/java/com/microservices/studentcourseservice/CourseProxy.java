package com.microservices.studentcourseservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service")
public interface CourseProxy {
	
	@GetMapping("courses/{id}")
	public CourseDTO getCourse(@PathVariable long id);
}
