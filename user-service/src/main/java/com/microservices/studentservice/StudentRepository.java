package com.microservices.studentservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
	public Student findByFirstName(String firstName);
}
