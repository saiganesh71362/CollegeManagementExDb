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

import com.acrunetit.college.entity.Students;
import com.acrunetit.college.globalexceptionhandle.StudentNotFoundException;
import com.acrunetit.college.serviceimpl.StudentServiceImpl;

@RestController
public class StudentController {

	private static final Logger logger = LogManager.getLogger(StudentController.class);

	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@GetMapping("/student/getStudentById/{studentId}")
	public ResponseEntity<Students> getStudentById(@PathVariable Integer studentId) throws StudentNotFoundException {
		logger.info("Received Request To Get Student By ID: {}", studentId);
		Students studentById = studentServiceImpl.getStudentById(studentId);
		logger.info("Successfully Retrieved Student With ID: {}", studentById);

		return new ResponseEntity<>(studentById, HttpStatus.OK);
	}

	@GetMapping("/student/getAllStudents")
	public ResponseEntity<List<Students>> getAllStudents() {
		logger.info("Received Request To Get All Students ");
		List<Students> allStudents = studentServiceImpl.getAllStudents();
		logger.info("Successfully Retrieved {} Students", allStudents.size());

		return new ResponseEntity<>(allStudents, HttpStatus.OK);
	}
}
