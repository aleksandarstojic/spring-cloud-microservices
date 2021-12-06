package com.microservices.facultyservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
	
	@Autowired
	FacultyRepository facultyRepository;

	public List<Faculty> getAllFaculties() {
		return facultyRepository.findAll();
	}

	public Faculty getFaculty(long id) {
		Optional<Faculty> faculty = facultyRepository.findById(id);
		if (faculty.isEmpty()) {
			throw new RuntimeException("Faculty with the id " + id + " does not exsist.");
		}
		return faculty.get();
	}

	public Faculty createFaculty(Faculty faculty) {
		Optional<Faculty> existingFaculty = facultyRepository.findById(faculty.getId());
		if(existingFaculty.isPresent()) {
			throw new RuntimeException("Faculty with the id " + faculty.getId() + " already exists.");
		}
		return facultyRepository.save(faculty);
	}

	public void removeFaculty(long id) {
		Optional<Faculty> entity = facultyRepository.findById(id);
		if (entity.isEmpty()) {
            throw new RuntimeException("Faculty not found");
        } else {
        	facultyRepository.delete(entity.get());
        }
	}

}
