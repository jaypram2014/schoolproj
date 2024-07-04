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

import com.catalyst.schoolproj.model.ClassMaster;
import com.catalyst.schoolproj.model.ClassMaster;
import com.catalyst.schoolproj.service.ClassService;

@RestController
@RequestMapping("/api/class-service")
public class ClassController {
	@Autowired
	private ClassService classService;
	
	@GetMapping("/classes")
	public List<ClassMaster> getAllClasses(){
		
		return classService.getAllClasses();				
	}
	
	@PostMapping("/class")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ClassMaster saveclassData(@RequestBody ClassMaster classData) {
		
		ClassMaster stud=null;
		try {
			stud = classService.saveClassData(classData);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return stud;
		
	}
	
	@PatchMapping("/class/{id}")
	@ResponseStatus (value = HttpStatus.CREATED)
	public ClassMaster updateclassData(@RequestBody ClassMaster classData, @PathVariable Long id ) {
		
		ClassMaster cls=null;
		try {
			cls = classService.updateClassData(classData, id);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return cls;
		
	}

}
