package com.catalyst.schoolproj.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "class_master", schema = "sms")
//@TableGenerator(name = "teacher_master_SEQ", table = "seq_id_table", schema = "sms", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1)
public class ClassMaster {

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "class_id")
    private Long id;

	@Column(name = "class_name")
    private String className;
	
	@OneToMany(mappedBy = "classMaster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)	 // mappedBy = "classMaster"
	List<StudentMaster> students = new ArrayList<StudentMaster>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@JsonManagedReference
	public List<StudentMaster> getStudents() {
		return students;
	}

	public void setStudents(List<StudentMaster> students) {
		this.students = students;
	}
	
	
	
}
