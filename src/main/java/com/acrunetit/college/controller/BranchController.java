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

import com.acrunetit.college.entity.Branch;
import com.acrunetit.college.globalexceptionhandle.StudentNotFoundException;
import com.acrunetit.college.serviceimpl.BranchServiceImpl;

@RestController
public class BranchController {

	private static final Logger logger = LogManager.getLogger(BranchController.class);

	@Autowired
	private BranchServiceImpl branchServiceImpl;


	@GetMapping("/branch/allBranches")
	public ResponseEntity<List<Branch>> getAllBranches() {
		logger.info("Received Request To Get All Branches");
		List<Branch> allBranches = branchServiceImpl.getAllBranches();
		logger.info("Successfully Retrieved {} Branches", allBranches.size());
		return new ResponseEntity<>(allBranches, HttpStatus.OK);
	}

	@GetMapping("/branch/getBranchById/{branchId}")
	public ResponseEntity<Branch> getBranchById(@PathVariable Integer branchId) throws StudentNotFoundException {
		logger.info("Received Request To Get Branch By ID: {}", branchId);
		Branch branchById = branchServiceImpl.getBranchById(branchId);
		logger.info("Successfully Retrieved Branch With ID: {}", branchId);
        return new ResponseEntity<>(branchById, HttpStatus.OK);
	}

}
