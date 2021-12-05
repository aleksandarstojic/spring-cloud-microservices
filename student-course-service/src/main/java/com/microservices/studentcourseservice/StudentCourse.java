package com.microservices.studentcourseservice;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(StudentCourse.class)
@Table(name = "student_course")
public class StudentCourse implements Serializable {
	
	@Id
	private Long studentId;
	
	@Id
	private Long courseId;
	
	public StudentCourse() {
		
	}
	
	public StudentCourse(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

	public Long getStudentId() {
		return studentId;
	}

	public Long getCourseId() {
		return courseId;
	}
	
}
