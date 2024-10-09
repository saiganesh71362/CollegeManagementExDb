package com.acrunetit.college;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.acrunetit.college.controller.StudentController;
import com.acrunetit.college.entity.Branch;
import com.acrunetit.college.entity.College;
import com.acrunetit.college.entity.Students;
import com.acrunetit.college.serviceimpl.StudentServiceImpl;

@SpringBootTest
class StudentControllerTest {

	@Mock
	StudentServiceImpl studentServiceImpl;

	@InjectMocks
	StudentController studentController;

	@Test
	@Order(1)
	void test_getAllStudents() {
		ArrayList<Students> studentsList = new ArrayList<>();

		College college1 = new College();
		college1.setCollegeId(1);
		college1.setCollegeName("Green Valley University");
		college1.setCollegeAddress("123 Green Valley St, Springfield");
		college1.setCollegeContact("123-456-7890");

		Branch branch1 = new Branch();
		branch1.setBranchId(1);
		branch1.setBranchName("Computer Science");
		branch1.setCollege(college1);

		Students student1 = new Students();
		student1.setStudentId(1);
		student1.setStudentName("Alice Johnson");
		student1.setStudentAddress("123 Apple St, Springfield");
		student1.setStudentContact("123-456-7890");
		student1.setBranch(branch1);

		Students student2 = new Students();
		student2.setStudentId(2);
		student2.setStudentName("Bob Smith");
		student2.setStudentAddress("456 Orange Ave, Springfield");
		student2.setStudentContact("987-654-3210");
		student2.setBranch(branch1);

		studentsList.add(student1);
		studentsList.add(student2);

		when(studentServiceImpl.getAllStudents()).thenReturn(studentsList);

		ResponseEntity<List<Students>> allStudents = studentController.getAllStudents();

		assertEquals(HttpStatus.OK, allStudents.getStatusCode());
		assertEquals(studentsList, allStudents.getBody());
	}

	@Test
	@Order(2)
	void test_getStudentById() {

		College college1 = new College();
		college1.setCollegeId(1);
		college1.setCollegeName("Green Valley University");
		college1.setCollegeAddress("123 Green Valley St, Springfield");
		college1.setCollegeContact("123-456-7890");

		Branch branch1 = new Branch();
		branch1.setBranchId(1);
		branch1.setBranchName("Computer Science");
		branch1.setCollege(college1);

		Students student1 = new Students();
		student1.setStudentId(1);
		student1.setStudentName("Alice Johnson");
		student1.setStudentAddress("123 Apple St, Springfield");
		student1.setStudentContact("123-456-7890");
		student1.setBranch(branch1);

		when(studentServiceImpl.getStudentById(1)).thenReturn(student1);
		ResponseEntity<Students> studentById = studentController.getStudentById(1);

		assertEquals(HttpStatus.OK, studentById.getStatusCode());
		assertEquals(student1, studentById.getBody());
	}
}
