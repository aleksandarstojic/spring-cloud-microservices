package com.microservices.studentservice;

public class Student {
	private String firstName;
	private String lastName;
	private int studentCardId;

	public Student() {
	}

	public Student(String firstName, String lastName, int studentCardId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentCardId = studentCardId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getStudentCardId() {
		return studentCardId;
	}

	public void setStudentCardId(int studentCardId) {
		this.studentCardId = studentCardId;
	}

}
