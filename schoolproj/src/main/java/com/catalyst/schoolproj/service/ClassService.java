package com.catalyst.schoolproj.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.schoolproj.model.ClassMaster;
import com.catalyst.schoolproj.repository.ClassRepo;

@Service
public class ClassService {

	@Autowired
	private ClassRepo classRepo;

	public List<ClassMaster> getAllClasses() {

		return classRepo.findAll();
	}

	public ClassMaster saveClassData(ClassMaster classData) {

		ClassMaster stud = null;
		try {
			stud = classRepo.save(classData);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return stud;

	}
	
	public ClassMaster updateClassData(ClassMaster classData, Long id) {
		ClassMaster cls = null;
		try {
			Optional<ClassMaster> existingClass = classRepo.findById(id);
			
			if(existingClass.isPresent()) {
				cls = existingClass.get();
				cls.setClassName(classData.getClassName());
				cls.setStudents(classData.getStudents());
				classRepo.save(cls);
			}
		
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return cls;
	}

	public ClassMaster getClassById(Long id) {
		
		
		return classRepo.findById(id).filter(Objects::nonNull).get();
	}

}
