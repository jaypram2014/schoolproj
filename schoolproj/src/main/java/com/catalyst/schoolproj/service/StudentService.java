package com.catalyst.schoolproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.catalyst.schoolproj.model.StudentMaster;
import com.catalyst.schoolproj.repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studRepo;

	public List<StudentMaster> getAllStudents() {

		return studRepo.findAll();
	}

	public StudentMaster saveStudentData(StudentMaster studentData) {

		StudentMaster stud = null;
		try {
			stud = studRepo.save(studentData);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return stud;

	}
	
	public StudentMaster updateStudentData(StudentMaster studentData, Long id) {
		StudentMaster stud = null;
		try {
			Optional<StudentMaster> existingStudent = studRepo.findById(id);
			
			if(existingStudent.isPresent()) {
				stud = existingStudent.get();
				stud.setFirstName(studentData.getFirstName());
				stud.setLastName(studentData.getLastName());
				
				studRepo.save(stud);
			}
		
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return stud;
	}

}
