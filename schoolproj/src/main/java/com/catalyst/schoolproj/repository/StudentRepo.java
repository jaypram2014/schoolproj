package com.catalyst.schoolproj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalyst.schoolproj.model.StudentMaster;

public interface StudentRepo extends JpaRepository<StudentMaster, Long>{

	Optional<StudentMaster> findByFirstNameAndLastName(String firstName, String lastName);
    // Nothing here to override
	
	
}
