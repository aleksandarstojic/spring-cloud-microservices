package com.microservices.studentcourseservice;

public class StudentCourseAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StudentCourseAlreadyExistsException(String message) {
		super(message);
	}
}
