package com.catalyst.schoolproj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalyst.schoolproj.model.SubjectMaster;

public interface SubjectRepo extends JpaRepository<SubjectMaster, Long> {

	Optional<SubjectMaster> findBySubjectName(String subjectName);
}
