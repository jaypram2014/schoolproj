package com.catalyst.schoolproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.schoolproj.model.StudentMaster;
import com.catalyst.schoolproj.repository.StudentRepo;

@RestController
@RequestMapping("/api/studentinfo")
public class StudentController {
	@Autowired
	private StudentRepo studRepo;
	
	@GetMapping("/students")
	public List<StudentMaster> getAllStudents(){
		
		return studRepo.findAll();				
	}
	
	@PostMapping("/saveStudentData")
	public StudentMaster saveStudentData(@RequestBody StudentMaster studentData) {
		
		StudentMaster stud=null;
		try {
			stud = studRepo.save(studentData);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stud;
		
	}

}
