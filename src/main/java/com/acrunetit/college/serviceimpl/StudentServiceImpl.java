package com.acrunetit.college.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acrunetit.college.appconstants.CollegeManagementAppConstants;
import com.acrunetit.college.entity.Students;
import com.acrunetit.college.globalexceptionhandle.StudentNotFoundException;
import com.acrunetit.college.repository.StudentRepository;
import com.acrunetit.college.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Students getStudentById(Integer studentId) throws StudentNotFoundException {
		logger.info("Fetching Student With ID: {}", studentId);

		Optional<Students> findById = studentRepository.findById(studentId);
		if (findById.isPresent()) {
			logger.info("Student Found: {}", findById.get());

			return findById.get();
		} else {
			logger.error("Student Not Found With ID: {}", studentId);

			throw new StudentNotFoundException(CollegeManagementAppConstants.STUDENT_NOT_FOUND + studentId);
		}
	}

	@Override
	public List<Students> getAllStudents() {
		logger.info("Fetching All Students");

		List<Students> findAll = studentRepository.findAll();
		logger.info("Number Of Students  found: {}", findAll.size());

		return findAll;
	}

}
