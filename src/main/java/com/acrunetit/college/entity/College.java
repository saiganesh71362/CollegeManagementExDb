package com.acrunetit.college.entity;

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
@Table(name = "college_table")
public class College {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private Integer collegeId;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "college_address")
    private String collegeAddress;

    @Column(name = "college_contact")
    private String collegeContact;

    @OneToMany(mappedBy = "college",cascade = CascadeType.ALL,fetch = FetchType.EAGER) 
    @JsonManagedReference
    private List<Branch> branches;
    
    

	public Integer getCollegeId() {
		return collegeId;
	}



	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}



	public String getCollegeName() {
		return collegeName;
	}



	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}



	public String getCollegeAddress() {
		return collegeAddress;
	}



	public void setCollegeAddress(String collegeAddress) {
		this.collegeAddress = collegeAddress;
	}



	public String getCollegeContact() {
		return collegeContact;
	}



	public void setCollegeContact(String collegeContact) {
		this.collegeContact = collegeContact;
	}



	public List<Branch> getBranches() {
		return branches;
	}



	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}



	 @Override
	    public String toString() {
	        return "College [collegeId=" + collegeId + ", collegeName=" + collegeName + 
	               ", collegeAddress=" + collegeAddress + ", collegeContact=" + collegeContact + 
	               ", branchesCount=" + (branches != null ? branches.size() : 0) + "]";
	    }
    
    
}
