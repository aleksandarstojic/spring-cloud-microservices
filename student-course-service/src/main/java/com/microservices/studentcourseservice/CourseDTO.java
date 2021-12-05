package com.microservices.studentcourseservice;

public class CourseDTO {
	private String courseName;

	public CourseDTO() {

	}

	public CourseDTO(String courseName) {
		super();
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}
}
