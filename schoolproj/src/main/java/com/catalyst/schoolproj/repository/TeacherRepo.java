package com.catalyst.schoolproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalyst.schoolproj.model.TeacherMaster;

public interface TeacherRepo extends JpaRepository<TeacherMaster, Integer>{
      // Nothing to override
}
