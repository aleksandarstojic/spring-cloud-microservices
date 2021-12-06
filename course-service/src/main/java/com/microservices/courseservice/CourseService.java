package com.microservices.courseservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourse(long id) {
		Optional<Course> course = courseRepository.findById(id);
		if (course.isEmpty()) {
			throw new CourseNotFoundException("Course with the id " + id + " does not exsist.");
		}
		return course.get();
	}

	public Course createCourse(Course course) {
		Optional<Course> existingCourse = courseRepository.findById(course.getId());
		if (existingCourse.isPresent()) {
			throw new CourseAlreadyExistsException("Course with the id " + course.getId() + " already exists.");
		}
		return courseRepository.save(course);
	}

	public void deleteCourse(long id) {
		Optional<Course> existingCourse = courseRepository.findById(id);
		if(existingCourse.isEmpty()) {
			throw new CourseNotFoundException("Course with the id " + id + " does not exsist.");
		}
		courseRepository.deleteById(id);
	}
}
