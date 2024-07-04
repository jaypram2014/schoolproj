package com.catalyst.schoolproj.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_master", schema = "sms")
//@TableGenerator(name = "student_master_SEQ", table = "seq_id_table", schema = "sms", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1)
public class StudentMaster implements Serializable {
	@Id	
	//@GeneratedValue(strategy=GenerationType.TABLE, generator = "student_master_SEQ")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "student_id")
    private Long id;

	@Column(name = "firstname")
    private String firstName;
	
	@Column(name = "lastname")
    private String lastName;
	
//	@Column(name = "permanent_address")
//    private String address;
//	
//	@Column(name = "date_of_birth")
//    private Date dob;
//	
	@Column(name = "guardian_name")
    private String guardianName;
	
//	@Column(name = "guardian_relation")
//    private String guardianRelation;
	
	@Column(name = "blood_group")
    private String bloodGroup;
	
//	@Column(name = "stud_regn_no")
//    private String studRegnNo;
//	
//	@Column(name = "remarks")
//    private String remarks;

//	@ManyToMany
//	@JoinTable(name = "student_teacher",joinColumns = @JoinColumn(name="student_id"))
//	private List<TeacherMaster> teachers = new ArrayList<TeacherMaster>();
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private ClassMaster classMaster;
	
    /////================= GETTER / SETTER ================ ////

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public Date getDob() {
//		return dob;
//	}
//
//	public void setDob(Date dob) {
//		this.dob = dob;
//	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

//	public String getGuardianRelation() {
//		return guardianRelation;
//	}
//
//	public void setGuardianRelation(String guardianRelation) {
//		this.guardianRelation = guardianRelation;
//	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

//	public String getStudRegnNo() {
//		return studRegnNo;
//	}
//
//	public void setStudRegnNo(String studRegnNo) {
//		this.studRegnNo = studRegnNo;
//	}
//
//	public String getRemarks() {
//		return remarks;
//	}
//
//	public void setRemarks(String remarks) {
//		this.remarks = remarks;
//	}

	@JsonBackReference
	public ClassMaster getClassMaster() {
		return classMaster;
	}

	public void setClassMaster(ClassMaster classMaster) {
		this.classMaster = classMaster;
	}

//	public List<TeacherMaster> getTeachers() {
//		return teachers;
//	}
//
//	public void setTeachers(List<TeacherMaster> teachers) {
//		this.teachers = teachers;
//	}
	
	

}
