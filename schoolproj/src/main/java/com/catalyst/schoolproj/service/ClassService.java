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
import com.catalyst.schoolproj.model.SubjectEnum;
import com.catalyst.schoolproj.model.SubjectMaster;
import com.catalyst.schoolproj.repository.ClassRepo;
import com.catalyst.schoolproj.repository.StudentRepo;
import com.catalyst.schoolproj.repository.SubjectRepo;

@Service
public class ClassService {

	@Autowired
	private ClassRepo classRepo;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private SubjectRepo subjectRepo;

	public List<ClassMaster> getAllClasses() {

		return classRepo.findAll();
	}

	public ClassMaster saveClassData(ClassMaster classData) {

		ClassMaster classMaster = null;
		try {
			// classMaster = classRepo.save(classData);  // OLD Method
			classMaster = createOrUpdateClass(classData, null);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return classMaster;

	}
	
	public ClassMaster updateClassData(ClassMaster classData, Long id) {
		ClassMaster cls = null;
		
//		Optional<ClassMaster> existingClass = classRepo.findById(id);
//		
//		if(existingClass.isPresent()) {
//			cls = existingClass.get();
//			cls.setClassName(classData.getClassName());
//			cls.setStudents(classData.getStudents());
//						
//			return classRepo.save(cls);
//			
//		}else {
//			throw new ResourceNotFoundException("Class not found with supplied ID :"+id);
//		}
		
		ClassMaster classMaster = createOrUpdateClass(classData, id);
		
		return classMaster;
	}

	public ClassMaster getClassById(Long id) {
				
		return classRepo.findById(id).filter(Objects::nonNull).get();
	}
	
	
	
	public ClassMaster createOrUpdateClass(ClassMaster classRequest, Long id) {
		
		ClassMaster classMaster = new ClassMaster();
		
		
		//if(StringUtils.hasText(classRequest.getId().toString())) {
		if(id!=null) {	
			classMaster= updateClassWorkflow(id, classMaster);
		}
		else {
			classMaster=createClassWorkFlow(classRequest);
		}
		
		
		//return classMaster;
		return classMaster;
		
	}
	
	/// CREATE CLASS - STUDENTS OPERATION :
	private ClassMaster createClassWorkFlow(ClassMaster classRequest) {
		System.out.println("CREATE CLASS WORKFLOW ----");
		ClassMaster classMaster=null;
		try {
			classMaster = new ClassMaster();
			classMaster.setClassName(classRequest.getClassName());
			
			List<StudentMaster> studentResqstList = classRequest.getStudents();
			List<StudentMaster> newStudentList = new ArrayList<StudentMaster>();
			
			// set Students
			for (StudentMaster studentMaster : studentResqstList) {
				StudentMaster newstudentMaster = new StudentMaster();
				newstudentMaster.setFirstName(studentMaster.getFirstName());
				newstudentMaster.setLastName(studentMaster.getLastName());
				newstudentMaster.setGuardianName(studentMaster.getGuardianName());
				newstudentMaster.setBloodGroup(studentMaster.getBloodGroup());
				newstudentMaster.setClassMaster(classMaster);
				
				 // Set Subjects
				 List<SubjectMaster> subjects = studentMaster.getSubjects();
				 List<SubjectMaster> acceptedSubjects = new ArrayList<SubjectMaster>(); 
				 for (SubjectMaster subjectMaster : subjects) {
					 if(SubjectEnum.valueOf(subjectMaster.getSubjectName())!=null) {
						 acceptedSubjects.add(subjectMaster);
					 }
				 }
				 
				 newstudentMaster.setSubjects(acceptedSubjects);
				 newStudentList.add(newstudentMaster);
			}
			
			classMaster.setStudents(newStudentList);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		
		return classRepo.save(classMaster);
		
	}

	/// UPDATE CLASS - STUDENT - SUBJECT OPERATION :
	private ClassMaster updateClassWorkflow(Long classId, ClassMaster classRequest) {
		System.out.println("CREATE CLASS WORKFLOW ----");
		Optional<ClassMaster> existingClass = classRepo.findById(classId);
		List<StudentMaster> existingStudents = new ArrayList<>();
		
		
		if (existingClass.isPresent()) {

			existingClass.get().setId(classRequest.getId());
			existingClass.get().setClassName(classRequest.getClassName());
			
			List<StudentMaster> studentResqstList = classRequest.getStudents();
			
			for (StudentMaster studentMaster : studentResqstList) {
				
				existingStudents.addAll(populateStudentWorkflow(studentMaster.getId(), studentMaster));
				
			}
			
			existingClass.get().setStudents(existingStudents);   // set the list
			return classRepo.save(existingClass.get());

		}

		else {
			throw new ResourceNotFoundException("Class not found for Class Id :: " + classId);
			
		}
	}
	
	private List<StudentMaster> populateStudentWorkflow(Long studentId, StudentMaster studentRequest) {

		Optional<StudentMaster> existingStudent = studentRepo.findById(studentId);
	
		List<StudentMaster> tempStudentList = new ArrayList<>();
		List<SubjectMaster> existingSubjects = new ArrayList<SubjectMaster>();
		
		if (existingStudent.isPresent()) {

			existingStudent.get().setFirstName(studentRequest.getFirstName());
			existingStudent.get().setLastName(studentRequest.getLastName());
			existingStudent.get().setBloodGroup(studentRequest.getBloodGroup());
			existingStudent.get().setGuardianName(studentRequest.getGuardianName());
			
			List<SubjectMaster> subjectList = studentRequest.getSubjects();
						
			for (SubjectMaster subjectMaster : subjectList) {
								
				existingSubjects.addAll(populateSubjectWorkflow(subjectMaster.getSubjId(), subjectMaster));
				
			}
			
			existingStudent.get().setSubjects(existingSubjects);   // set the list
			tempStudentList.add(existingStudent.get());
			
			return tempStudentList;

		}

		else
			throw new ResourceNotFoundException("Student not found for StudentId Id :: " + studentId);
	}
	
	
	private List<SubjectMaster> populateSubjectWorkflow(Long id, SubjectMaster subjectRequest){
		
		Optional<SubjectMaster> existingSubject = subjectRepo.findById(id);
		List<SubjectMaster> tempSubjectList = new ArrayList<>();
		
		if (existingSubject.isPresent()) {

			existingSubject.get().setSubjectName(subjectRequest.getSubjectName());
			
			tempSubjectList.add(existingSubject.get());
			return tempSubjectList;
		}else
			 throw new ResourceNotFoundException("Subject not found");
		
		
	}

	private ClassMaster createClassWorkFlow() {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	// Added By Chayan
	/*
	 * public void createOrUpdateClass(ClassMaster classRequest) { ClassMaster
	 * classEntity = classRepo.findByClassName(classRequest.getClassName()); if
	 * (classEntity == null) { classEntity = new ClassMaster();
	 * classEntity.setClassName(classRequest.getClassName()); }
	 * 
	 * for (StudentMaster studentRequest : classRequest.getStudents()) {
	 * //Optional<StudentMaster> student =
	 * studentRepo.findByFirstNameAndLastName(studentRequest.getFirstName(),
	 * studentRequest.getLastName()); //
	 * 
	 * if (StringUtils.isEmpty(studentRequest.getId())) { // New Student
	 * 
	 * StudentMaster smaster = new StudentMaster(); List<StudentMaster> listStud =
	 * classRequest.getStudents(); // List<StudentMaster> listTempStud = new
	 * ArrayList<StudentMaster>();
	 * 
	 * for (StudentMaster studentMaster : listStud) {
	 * smaster.setFirstName(studentMaster.getFirstName());
	 * smaster.setLastName(studentMaster.getLastName());
	 * smaster.setGuardianName(studentMaster.getGuardianName());
	 * smaster.setBloodGroup(studentMaster.getBloodGroup());
	 * 
	 * listTempStud.add(smaster);
	 * 
	 * } classEntity.setStudents(listTempStud);
	 * 
	 * }else { //update Student Optional<StudentMaster> existingStudent =
	 * studentRepo.findById(studentRequest.getId());
	 * 
	 * List<StudentMaster> listStud = classRequest.getStudents(); //
	 * List<StudentMaster> listTempStud = new ArrayList<StudentMaster>();
	 * 
	 * for (StudentMaster studentMaster : listStud) {
	 * existingStudent.get().setFirstName(studentMaster.getFirstName());
	 * existingStudent.get().setLastName(studentMaster.getLastName());
	 * existingStudent.get().setGuardianName(studentMaster.getGuardianName());
	 * existingStudent.get().setBloodGroup(studentMaster.getBloodGroup());
	 * 
	 * listTempStud.add(existingStudent.get());
	 * 
	 * } classEntity.setStudents(listTempStud);
	 * 
	 * }
	 * 
	 * for (SubjectMaster subjectRequest : studentRequest.getSubjects()) { //
	 * Subject subject = subjectRepository.findById(subjectRequest.getSubjectId());
	 * // if (subject == null) { // subject = new Subject(); //
	 * subject.setId(subjectRequest.getSubjectId()); //
	 * subject.setName(subjectRequest.getSubjectName()); //
	 * subjectRepository.save(subject); } // student.getSubjects().add(subject); }
	 * 
	 * //classEntity.getStudents().add(student); }
	 * 
	 * classRepository.save(classEntity); }
	 */

}
