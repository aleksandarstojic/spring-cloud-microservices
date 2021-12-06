package com.microservices.departmentservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.departmentservice.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	public Department getDepartment(long id) {
		Optional<Department> department = departmentRepository.findById(id);
		if (department.isEmpty()) {
			throw new RuntimeException("Department with the id " + id + " does not exsist.");
		}
		return department.get();
	}

	public Department createDepartment(Department department) {
		Optional<Department> existingDepartment = departmentRepository.findById(department.getId());
		if(existingDepartment.isPresent()) {
			throw new RuntimeException("Department with the id " + department.getId() + " already exists.");
		}
		return departmentRepository.save(department);
	}

	public void removeDepartment(long id) {
		Optional<Department> entity = departmentRepository.findById(id);
		if (entity.isEmpty()) {
            throw new RuntimeException("Department not found");
        } else {
        	departmentRepository.delete(entity.get());
        }
	}

}
