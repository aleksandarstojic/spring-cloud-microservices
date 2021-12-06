package com.microservices.facultyservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{
	public Faculty findByName(String name);
}
