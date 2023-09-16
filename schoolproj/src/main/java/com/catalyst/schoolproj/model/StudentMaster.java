package com.catalyst.schoolproj.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;

@Entity
@Table(name = "student_master", schema = "sms")
@TableGenerator(name = "student_master_SEQ", table = "seq_id_table", schema = "sms", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1)
public class StudentMaster implements Serializable {
	@Id	
	@GeneratedValue(strategy=GenerationType.TABLE, generator = "student_master_SEQ")
	@Column(name = "student_id")
    private Integer id;

	@Column(name = "firstname")
    private String firstname;
	
	@Column(name = "lastname")
    private String lastname;
	
	@Column(name = "permanent_address")
    private String address;
	
	@Column(name = "date_of_birth")
    private Date dob;
	
	@Column(name = "guardian_name")
    private String guardianName;
	
	@Column(name = "guardian_relation")
    private String guardianRelation;
	
	@Column(name = "blood_group")
    private String bloodGroup;
	
	@Column(name = "stud_regn_no")
    private String studRegnNo;
	
	@Column(name = "remarks")
    private String remarks;

    /////================= GETTER / SETTER ================ ////

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianRelation() {
		return guardianRelation;
	}

	public void setGuardianRelation(String guardianRelation) {
		this.guardianRelation = guardianRelation;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getStudRegnNo() {
		return studRegnNo;
	}

	public void setStudRegnNo(String studRegnNo) {
		this.studRegnNo = studRegnNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
