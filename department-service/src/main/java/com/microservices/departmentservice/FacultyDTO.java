package com.microservices.departmentservice;

public class FacultyDTO {
	private String facultyName;

	public FacultyDTO() {

	}

	public FacultyDTO(String facultyName) {
		super();
		this.facultyName = facultyName;
	}

	public String getFacultyName() {
		return facultyName;
	}
}
