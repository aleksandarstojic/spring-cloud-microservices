package com.microservices.facultyservice;

public class FacultyNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	FacultyNotFoundException(String message) {
		super(message);
	}
}
