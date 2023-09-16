package com.catalyst.schoolproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.schoolproj.model.TeacherMaster;
import com.catalyst.schoolproj.repository.TeacherRepo;

@RestController
@RequestMapping("/api/teacherinfo")
public class TeacherController {
	@Autowired
	private TeacherRepo tchrRepo;
	
	@GetMapping("/teachers")
	public List<TeacherMaster> getAllStudents(){
		
		return tchrRepo.findAll();				
	}
	
	@PostMapping("/saveTeacherData")
	public TeacherMaster saveTeacherData(@RequestBody TeacherMaster teacherData) {
		
		TeacherMaster tchr=null;
		try {
			tchr = tchrRepo.save(teacherData);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return tchr;
		
	}

}
