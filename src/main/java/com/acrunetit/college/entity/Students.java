package com.acrunetit.college.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students_table")
@JsonIgnoreProperties({ "branch" })
public class Students {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Integer studentId;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "student_address")
	private String studentAddress;

	@Column(name = "student_contact")
	private String studentContact;

	@ManyToOne
	@JoinColumn(name = "branch_id")
	@JsonBackReference
	private Branch branch;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentContact() {
		return studentContact;
	}

	public void setStudentContact(String studentContact) {
		this.studentContact = studentContact;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Students [studentId=" + studentId + ", studentName=" + studentName + ", studentAddress="
				+ studentAddress + ", studentContact=" + studentContact + ", branchId="
				+ (branch != null ? branch.getBranchId() : "null") + "]";
	}

}
