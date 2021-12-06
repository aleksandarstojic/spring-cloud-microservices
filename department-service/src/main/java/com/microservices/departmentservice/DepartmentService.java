package com.microservices.departmentservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.departmentservice.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	private RestTemplate restTemplate = this.restTemplate();

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	FacultyProxy facultyProxy;
	
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	public Department getDepartment(long id) {
		Optional<Department> department = departmentRepository.findById(id);
		if (department.isEmpty()) {
			throw new DepartmentNotFoundException("Department with the id " + id + " does not exsist.");
		}
		return department.get();
	}

	public Department createDepartment(Department department) {
		FacultyDTO facultyDTO = facultyProxy.getFaculty(department.getFacultyId());
		
		if (facultyDTO == null) {
			throw new EntityNotFoundException("Faculty with the given id does not exist.");
		}

		Optional<Department> existingDepartment = departmentRepository.findById(department.getId());
		if (existingDepartment.isPresent()) {
			throw new DepartmentAlreadyExistsException(
					"Department with the id " + department.getId() + " already exists.");
		}
		return departmentRepository.save(department);
	}

	public void removeDepartment(long id) {
		Optional<Department> entity = departmentRepository.findById(id);
		if (entity.isEmpty()) {
			throw new DepartmentNotFoundException("Department with the id " + id + " does not exist.");
		} else {
			departmentRepository.delete(entity.get());
		}
	}

}
