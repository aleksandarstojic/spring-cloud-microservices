package com.microservices.studentservice;

public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	StudentNotFoundException(String message) {
		super(message);
	}
}
