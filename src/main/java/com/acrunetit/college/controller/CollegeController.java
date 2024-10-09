package com.acrunetit.college.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.acrunetit.college.entity.College;
import com.acrunetit.college.globalexceptionhandle.StudentNotFoundException;
import com.acrunetit.college.serviceimpl.CollegeServiceImpl;

@RestController
public class CollegeController {

	private static final Logger logger = LogManager.getLogger(CollegeController.class);
	@Autowired
	private CollegeServiceImpl collegeServiceImpl;

	@GetMapping("/college/allCollegse")
	public ResponseEntity<List<College>> getAllColleges() {
		logger.info("Received Request To Get All Colleges ");
		List<College> allColleges = collegeServiceImpl.getAllColleges();
		logger.info("Successfully Retrieved {} Collges", allColleges.size());
		return new ResponseEntity<>(allColleges, HttpStatus.OK);
	}

	@GetMapping("/college/findByCollegeById/{collegeId}")
	public ResponseEntity<College> getCollegeById(@PathVariable Integer collegeId) throws StudentNotFoundException  {
		logger.info("Received Request To Get College By ID: {}", collegeId);
		College collegeById = collegeServiceImpl.getCollegeById(collegeId);
		logger.info("Successfully Retrieved College With ID: {}", collegeById);

		return new ResponseEntity<>(collegeById, HttpStatus.OK);
	}
}
