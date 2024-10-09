package com.acrunetit.college.service;

import java.util.List;

import com.acrunetit.college.entity.College;
import com.acrunetit.college.globalexceptionhandle.StudentNotFoundException;

public interface CollegeService {

	List<College> getAllColleges();

	College getCollegeById(Integer collegeId) throws StudentNotFoundException;

}
