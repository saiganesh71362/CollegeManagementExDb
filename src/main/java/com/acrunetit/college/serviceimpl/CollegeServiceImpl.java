package com.acrunetit.college.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acrunetit.college.entity.College;
import com.acrunetit.college.globalexceptionhandle.CollegeNotFoundException;
import com.acrunetit.college.repository.CollegeRepository;
import com.acrunetit.college.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {

	private static final Logger logger = LogManager.getLogger(CollegeServiceImpl.class);

	@Autowired
	private CollegeRepository collegeRepository;

	@Override
	public List<College> getAllColleges() {
		logger.info("Fetching All Colleges");
		List<College> findAll = collegeRepository.findAll();
		logger.info("Number Of Colleges  found: {}", findAll.size());

		return findAll;
	}

	@Override
	public College getCollegeById(Integer collegeId) throws CollegeNotFoundException {
		logger.info("Fetching College With ID: {}", collegeId);

		Optional<College> findById = collegeRepository.findById(collegeId);

		if (findById.isPresent()) {
			logger.info("College Found: {}", findById.get());

			return findById.get();
		} else {
			logger.error("College Not Found With ID: {}", collegeId);

			throw new CollegeNotFoundException("College Id Not Found :" + collegeId);
		}
	}

}
