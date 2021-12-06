package com.microservices.facultyservice;

public class FacultyAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	FacultyAlreadyExistsException(String message) {
		super(message);
	}
}
