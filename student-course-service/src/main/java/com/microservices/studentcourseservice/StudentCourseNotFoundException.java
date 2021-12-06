package com.microservices.studentcourseservice;

public class StudentCourseNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	StudentCourseNotFoundException(String message) {
		super(message);
	}
}
