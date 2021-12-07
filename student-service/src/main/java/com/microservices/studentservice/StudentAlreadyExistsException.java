package com.microservices.studentservice;

public class StudentAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StudentAlreadyExistsException(String message) {
		super(message);
	}
}
