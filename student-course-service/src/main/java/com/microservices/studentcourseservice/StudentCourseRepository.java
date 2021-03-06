package com.microservices.studentcourseservice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
	List<StudentCourse> findByStudentId(Long studentId);
	List<StudentCourse> findByCourseId(Long courseId);
	Optional<StudentCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
