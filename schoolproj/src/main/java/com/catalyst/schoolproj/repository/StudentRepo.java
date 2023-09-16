package com.catalyst.schoolproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalyst.schoolproj.model.StudentMaster;

public interface StudentRepo extends JpaRepository<StudentMaster, Integer>{
    // Nothing here to override
}
