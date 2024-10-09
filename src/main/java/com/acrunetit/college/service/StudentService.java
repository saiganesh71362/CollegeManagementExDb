package com.acrunetit.college.service;

import java.util.List;

import com.acrunetit.college.entity.Students;
import com.acrunetit.college.globalexceptionhandle.StudentNotFoundException;

public interface StudentService {

	Students getStudentById(Integer studentId) throws StudentNotFoundException;

	List<Students> getAllStudents();

}
