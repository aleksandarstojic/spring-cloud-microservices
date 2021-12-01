package com.microservices.courseservice;

public class CourseNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	CourseNotFoundException(String message) {
		super(message);
	}
}
