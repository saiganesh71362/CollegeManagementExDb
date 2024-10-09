package com.acrunetit.college.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "branch_table")
@JsonIgnoreProperties({"college"})
public class Branch {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Integer branchId;

    @Column(name = "branch_name")
    private String branchName;

    @ManyToOne
    @JoinColumn(name = "college_id")
    @JsonBackReference
    private College college;  

    @OneToMany(mappedBy = "branch" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)  
    @JsonManagedReference
    private List<Students> students;
    
    

    public Integer getBranchId() {
		return branchId;
	}



	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}



	public String getBranchName() {
		return branchName;
	}



	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}



	public College getCollege() {
		return college;
	}



	public void setCollege(College college) {
		this.college = college;
	}



	public List<Students> getStudents() {
		return students;
	}



	public void setStudents(List<Students> students) {
		this.students = students;
	}



//	@Override
//	public String toString() {
//		return "Branch [branchId=" + branchId + 
//				", branchName=" + branchName +
//				", college=" + college + 
//				", students="+ students + "]";
//	}



	@Override
    public String toString() {
        return "Branch [branchId=" + branchId +
        		", branchName=" + branchName + 
               ", collegeId=" + (college != null ? college.getCollegeId() : "null") + 
               ", studentsCount=" + (students != null ? students.size() : 0) + "]";
    }
	
	
	
   
    
}
