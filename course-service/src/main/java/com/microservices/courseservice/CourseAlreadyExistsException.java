package com.microservices.courseservice;

public class CourseAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	CourseAlreadyExistsException(String message) {
		super(message);
	}
}
