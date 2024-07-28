package com.catalyst.schoolproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.catalyst.schoolproj.exception.ResourceNotFoundException;
import com.catalyst.schoolproj.model.SubjectEnum;
import com.catalyst.schoolproj.model.SubjectMaster;
import com.catalyst.schoolproj.repository.SubjectRepo;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepo subjectRepo;
	
	// find By ID
	public SubjectMaster getSubject(Long id) {
		SubjectMaster subject = null;
		Optional<SubjectMaster> fetchedSubject = subjectRepo.findById(id);
		if(fetchedSubject.isPresent()) {
			subject = fetchedSubject.get();
		}
		
		return subject;
	}

	// find ALL
	public List<SubjectMaster> getAllSubjects() {

		return subjectRepo.findAll();
	}
	
	// find By Subject Name
	public SubjectMaster getSubjectByName(String subjectName) {
		SubjectMaster subject = null;
		Optional<SubjectMaster> fetchedSubject = subjectRepo.findBySubjectName(subjectName);
		if(fetchedSubject.isPresent()) {
			subject = fetchedSubject.get();
		}
		
		return subject;
	}

	// CREATE NEW SUBJECT
	public SubjectMaster saveSubjectData(SubjectMaster subjectData) {

		SubjectMaster subject = null;
		try {
			SubjectMaster subjectNew = new SubjectMaster();
			if(SubjectEnum.valueOf(subjectData.getSubjectName())!=null) {
				subjectNew.setSubjectName(subjectData.getSubjectName());
				subject = subjectRepo.save(subjectData);
			}else {
				System.out.println("Please enter proper subject name");
			}

		} catch (Exception e) {			
			throw new ResourceNotFoundException("Subject could not be saved :" + subjectData.getSubjectName());
		
		}

		return subject;

	}
	
	// UPDATE EXISTING SUBJECT
	public SubjectMaster updateSubjectData(SubjectMaster subjectData, Long id) {
		SubjectMaster subject = null;
		try {
			Optional<SubjectMaster> existingSubject = subjectRepo.findById(id);
			
			if(existingSubject.isPresent()) {
				subject = existingSubject.get();
				if(SubjectEnum.valueOf(subjectData.getSubjectName())!=null) {
					subject.setSubjectName(subjectData.getSubjectName());
					subject = subjectRepo.save(subjectData);
				}else {
					System.out.println("Please enter proper subject name");
				}
				
				
			}
		
			
		} catch (Exception e) {			
			throw new ResourceNotFoundException("Subject not found for ID :: " + id);
		}
		
		return subject;
	}

}
