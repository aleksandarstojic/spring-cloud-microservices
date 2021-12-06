package com.microservices.courseservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign will automatically talk to Eureka service registry and load balance between multiple instances of microservices
@FeignClient(name="department-service")
public interface DepartmentProxy {
	
	@GetMapping("departments/{id}")
	public DepartmentDTO getDepartment(@PathVariable long id);
}
