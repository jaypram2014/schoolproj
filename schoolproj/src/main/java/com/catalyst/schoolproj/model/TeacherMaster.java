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
@Table(name = "teacher_master", schema = "sms")
@TableGenerator(name = "teacher_master_SEQ", table = "seq_id_table", schema = "sms", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1)
public class TeacherMaster implements Serializable {
	@Id	
	@GeneratedValue(strategy=GenerationType.TABLE, generator = "teacher_master_SEQ")
	@Column(name = "teacher_id")
    private Integer id;

	@Column(name = "teacher_fname")
    private String firstname;
	
	@Column(name = "teacher_lname")
    private String lastname;
	
	@Column(name = "permanent_address")
    private String address;
	
	@Column(name = "qualification")
    private String qualification;
	
	@Column(name = "extra_qualification")
    private String extraQualification;
	
	@Column(name = "dob")
    private Date dob;
	
	@Column(name = "id_number")
    private String idnumber;
	
	@Column(name = "subject_category")
    private String subjectCategory;	
	
	@Column(name = "remarks")
    private String remarks;
	
	
	////////============== GETTERS / SETTERS METHODS ================/////////

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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getExtraQualification() {
		return extraQualification;
	}

	public void setExtraQualification(String extraQualification) {
		this.extraQualification = extraQualification;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getSubjectCategory() {
		return subjectCategory;
	}

	public void setSubjectCategory(String subjectCategory) {
		this.subjectCategory = subjectCategory;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
