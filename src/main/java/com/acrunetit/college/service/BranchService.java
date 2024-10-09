package com.acrunetit.college.service;

import java.util.List;

import com.acrunetit.college.entity.Branch;
import com.acrunetit.college.globalexceptionhandle.StudentNotFoundException;

public interface BranchService {

	List<Branch> getAllBranches();

	Branch getBranchById(Integer branchId) throws StudentNotFoundException;

}
