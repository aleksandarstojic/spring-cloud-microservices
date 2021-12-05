package com.microservices.studentcourseservice;

public class StudentDTO {
	private String firstName;
	private String lastName;

	public StudentDTO() {

	}

	public StudentDTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
