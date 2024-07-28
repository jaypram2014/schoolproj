package com.catalyst.schoolproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.schoolproj.model.StudentMaster;
import com.catalyst.schoolproj.model.SubjectMaster;
import com.catalyst.schoolproj.service.SubjectService;



@RestController
@RequestMapping("/api/subject-service")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("/subject/{id}")
	public SubjectMaster getSubjectById(@PathVariable Long id){
		
		return subjectService.getSubject(id);				
	}
	
	@GetMapping("/subjects")
	public List<SubjectMaster> getAllSubjects(){
		
		return subjectService.getAllSubjects();				
	}
	
	@PostMapping("/subject")
	public SubjectMaster saveSubjectData(@RequestBody SubjectMaster subjectData) {
		
		SubjectMaster subj=null;
		try {
			subj = subjectService.saveSubjectData(subjectData);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return subj;
		
	}
	
	@PatchMapping("/subject/{id}")
	@ResponseStatus (value = HttpStatus.CREATED)
	public SubjectMaster updateSubjectData(@RequestBody SubjectMaster subjectData, @PathVariable Long id ) {
		
		SubjectMaster subj=null;
		try {
			subj = subjectService.updateSubjectData(subjectData, id);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return subj;
		
	}
	
}
