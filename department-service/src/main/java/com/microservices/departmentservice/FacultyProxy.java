package com.microservices.departmentservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Feign will automatically talk to Eureka service registry and load balance between multiple instances of microservices
@FeignClient(name="faculty-service")
public interface FacultyProxy {
	
	@GetMapping("faculties/{id}")
	public FacultyDTO getFaculty(@PathVariable long id);
}
