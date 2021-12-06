package com.microservices.departmentservice;

public class DepartmentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	DepartmentNotFoundException(String message) {
		super(message);
	}

}
