package com.microservices.courseservice;

public class DepartmentDTO {
	private String departmentName;

	public DepartmentDTO() {

	}

	public DepartmentDTO(String departmentName) {
		super();
		this.departmentName = departmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}
}
