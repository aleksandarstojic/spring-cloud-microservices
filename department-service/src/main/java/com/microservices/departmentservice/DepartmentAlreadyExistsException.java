package com.microservices.departmentservice;

public class DepartmentAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	DepartmentAlreadyExistsException(String message) {
		super(message);
	}
}
