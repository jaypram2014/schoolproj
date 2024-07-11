package com.catalyst.schoolproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalyst.schoolproj.model.SubjectMaster;

public interface SubjectRepo extends JpaRepository<SubjectMaster, Long> {

}
