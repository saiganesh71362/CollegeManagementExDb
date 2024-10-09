package com.acrunetit.college;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acrunetit.college.controller.StudentController;
import com.acrunetit.college.entity.Branch;
import com.acrunetit.college.entity.College;
import com.acrunetit.college.entity.Students;
import com.acrunetit.college.serviceimpl.StudentServiceImpl;

@ComponentScan("com.acrunetit.college")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = StudentControllerMockMvcTest.class)
class StudentControllerMockMvcTest {

	@Autowired
	MockMvc mockMvc;
	@Mock
	StudentServiceImpl studentServiceImpl;
	@InjectMocks
	StudentController studentController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}

	@Test
	@Order(1)
	void test_getAllStudents() throws Exception {
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
		this.mockMvc.perform(get("/student/getAllStudents")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@Order(2)
	void test_getStudentById() throws Exception {

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

		this.mockMvc.perform(get("/student/getStudentById/{studentId}", 1))
		.andExpect(status()
	    .isOk()).andDo(print());
	}

}
