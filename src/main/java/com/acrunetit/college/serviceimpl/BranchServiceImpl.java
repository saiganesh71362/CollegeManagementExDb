package com.acrunetit.college.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acrunetit.college.appconstants.CollegeManagementAppConstants;
import com.acrunetit.college.entity.Branch;
import com.acrunetit.college.globalexceptionhandle.BranchNotFoundException;
import com.acrunetit.college.repository.BranchRepository;
import com.acrunetit.college.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

	private static final Logger logger = LogManager.getLogger(BranchServiceImpl.class);

	@Autowired
	private BranchRepository branchRepository;

	@Override
	public List<Branch> getAllBranches() {

		logger.info("Fetching All Branches");
		List<Branch> findAll = branchRepository.findAll();
		logger.info("Number Of Branches Found: {}", findAll.size());
		return findAll;

	}

	@Override
	public Branch getBranchById(Integer branchId) throws BranchNotFoundException {

		logger.info("Fetching Branch With ID: {}", branchId);
		Optional<Branch> findById = branchRepository.findById(branchId);
		if (findById.isPresent()) {
			logger.info("Branch Found: {}", findById.get());
			return findById.get();
		} else {
			logger.error("Branch Not Found With ID: {}", branchId);
			throw new BranchNotFoundException(CollegeManagementAppConstants.BRANCH_NOT_FOUND + branchId);
		}
	}

}
