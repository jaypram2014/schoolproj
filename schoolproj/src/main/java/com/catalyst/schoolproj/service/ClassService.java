package com.catalyst.schoolproj.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.catalyst.schoolproj.exception.ResourceNotFoundException;
import com.catalyst.schoolproj.model.ClassMaster;
import com.catalyst.schoolproj.model.StudentMaster;
import com.catalyst.schoolproj.model.SubjectMaster;
import com.catalyst.schoolproj.repository.ClassRepo;
import com.catalyst.schoolproj.repository.StudentRepo;

@Service
public class ClassService {

	@Autowired
	private ClassRepo classRepo;
	
	@Autowired
	private StudentRepo studentRepo;

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
		
		Optional<ClassMaster> existingClass = classRepo.findById(id);
		
		if(existingClass.isPresent()) {
			cls = existingClass.get();
			cls.setClassName(classData.getClassName());
			cls.setStudents(classData.getStudents());
						
			return classRepo.save(cls);
			
		}else {
			throw new ResourceNotFoundException("Class not found with supplied ID :"+id);
		}
		
		//return cls;
	}

	public ClassMaster getClassById(Long id) {
				
		return classRepo.findById(id).filter(Objects::nonNull).get();
	}
	
	// Added By Chayan
	public void createOrUpdateClass(ClassMaster classRequest) {
	    ClassMaster classEntity = classRepo.findByClassName(classRequest.getClassName());
	    if (classEntity == null) {
	        classEntity = new ClassMaster();
	        classEntity.setClassName(classRequest.getClassName());
	    }
	    
	    for (StudentMaster studentRequest : classRequest.getStudents()) {	        
			//Optional<StudentMaster> student = studentRepo.findByFirstNameAndLastName(studentRequest.getFirstName(), studentRequest.getLastName());   //
	    	
	    	if (StringUtils.isEmpty(studentRequest.getId())) {
	    		// New Student
	    		
	        	StudentMaster smaster = new StudentMaster();
	        	List<StudentMaster> listStud = classRequest.getStudents();  //
	        	List<StudentMaster> listTempStud = new ArrayList<StudentMaster>();
	        	
	        	for (StudentMaster studentMaster : listStud) {
	        		smaster.setFirstName(studentMaster.getFirstName());
		        	smaster.setLastName(studentMaster.getLastName());
		        	smaster.setGuardianName(studentMaster.getGuardianName());
		        	smaster.setBloodGroup(studentMaster.getBloodGroup());
		        	
		        	listTempStud.add(smaster);
		        	
				}
	        	classEntity.setStudents(listTempStud);
	        	
	    	}else {
	    		//update Student
	    		Optional<StudentMaster> existingStudent = studentRepo.findById(studentRequest.getId());
	    		
	    		List<StudentMaster> listStud = classRequest.getStudents();  //
	        	List<StudentMaster> listTempStud = new ArrayList<StudentMaster>();
	        	
	        	for (StudentMaster studentMaster : listStud) {
	        		smaster.setId(studentRequest.getId());
	        		smaster.setFirstName(studentMaster.getFirstName());
		        	smaster.setLastName(studentMaster.getLastName());
		        	smaster.setGuardianName(studentMaster.getGuardianName());
		        	smaster.setBloodGroup(studentMaster.getBloodGroup());
		        	
		        	listTempStud.add(smaster);
		        	
				}
	        	classEntity.setStudents(listTempStud);
	    		
	    	}

	        for (SubjectMaster subjectRequest : studentRequest.getSubjects()) {
	            Subject subject = subjectRepository.findById(subjectRequest.getSubjectId());
	            if (subject == null) {
	                subject = new Subject();
	                subject.setId(subjectRequest.getSubjectId());
	                subject.setName(subjectRequest.getSubjectName());
	                subjectRepository.save(subject);
	            }
	            student.getSubjects().add(subject);
	        }

	        classEntity.getStudents().add(student);
	    }

	    classRepository.save(classEntity);
	}

}
