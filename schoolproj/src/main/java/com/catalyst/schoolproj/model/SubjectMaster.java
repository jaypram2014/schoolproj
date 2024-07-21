package com.catalyst.schoolproj.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject_master", schema = "sms")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SubjectMaster {
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "subject_id")
    private Long subjId;

	@Column(name = "subject_name")
    private String subjectName;
	
	
	@ManyToMany(fetch = FetchType.LAZY)		
	List<StudentMaster> students = new ArrayList<StudentMaster>();
			

	public Long getSubjId() {
		return subjId;
	}

	public void setSubjId(Long subjId) {
		this.subjId = subjId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


//	@JsonInclude(value =Include.NON_NULL)
	@JsonIgnoreProperties
	public List<StudentMaster> getStudents() {
		return students;
	}
//
	public void setStudents(List<StudentMaster> students) {
		this.students = students;
	}
	
	

}
