package com.catalyst.schoolproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalyst.schoolproj.model.ClassMaster;

public interface ClassRepo extends JpaRepository<ClassMaster, Long>{

	public ClassMaster findByClassName(String className);
    // Nothing here to override
	
	
}
