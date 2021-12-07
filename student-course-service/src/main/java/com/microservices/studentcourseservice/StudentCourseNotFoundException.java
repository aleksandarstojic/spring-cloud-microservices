package com.microservices.studentcourseservice;

public class StudentCourseNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public StudentCourseNotFoundException(String message) {
		super(message);
	}
}
