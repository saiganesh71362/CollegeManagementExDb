package com.acrunetit.college;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.acrunetit.college.controller.CollegeController;
import com.acrunetit.college.entity.Branch;
import com.acrunetit.college.entity.College;
import com.acrunetit.college.serviceimpl.CollegeServiceImpl;

@ComponentScan("com.acrunetit.college")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { CollegeControllerMockMvcTest.class })
class CollegeControllerMockMvcTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	CollegeServiceImpl collegeServiceImpl;
	@InjectMocks
	CollegeController collegeController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(collegeController).build();
	}

	@Test
	@Order(1)
	void test_getAllColleges() throws Exception {
		ArrayList<College> collegeList = new ArrayList<>();

		College college1 = new College();
		college1.setCollegeId(1);
		college1.setCollegeName("Green Valley University");
		college1.setCollegeAddress("123 Green Valley St, Springfield");
		college1.setCollegeContact("123-456-7890");

		Branch branch1 = new Branch();
		branch1.setBranchId(1);
		branch1.setBranchName("Computer Science");
		branch1.setCollege(college1);

		Branch branch2 = new Branch();
		branch2.setBranchId(2);
		branch2.setBranchName("Mechanical Engineering");
		branch2.setCollege(college1);

		List<Branch> branches1 = new ArrayList<>(Arrays.asList(branch1, branch2));
		college1.setBranches(branches1);

		College college2 = new College();
		college2.setCollegeId(2);
		college2.setCollegeName("Sunrise Institute of Technology");
		college2.setCollegeAddress("456 Sunrise Blvd, Sun City");
		college2.setCollegeContact("987-654-3210");

		Branch branch11 = new Branch();
		branch11.setBranchId(3);
		branch11.setBranchName("Civil Engineering");
		branch11.setCollege(college2);

		Branch branch22 = new Branch();
		branch22.setBranchId(4);
		branch22.setBranchName("Electrical Engineering");
		branch22.setCollege(college2);

		List<Branch> branches2 = new ArrayList<>(Arrays.asList(branch11, branch22));
		college2.setBranches(branches2);

		collegeList.add(college1);
		collegeList.add(college2);

		when(collegeServiceImpl.getAllColleges()).thenReturn(collegeList);

		this.mockMvc.perform(get("/college/allCollegse"))
		.andExpect(status().isOk())
		.andDo(print());

	}

	@Test
	@Order(2)
	void test_getCollegeById() throws Exception {
		College college1 = new College();
		college1.setCollegeId(1);
		college1.setCollegeName("Green Valley University");
		college1.setCollegeAddress("123 Green Valley St, Springfield");
		college1.setCollegeContact("123-456-7890");

		Branch branch1 = new Branch();
		branch1.setBranchId(1);
		branch1.setBranchName("Computer Science");
		branch1.setCollege(college1);

		Branch branch2 = new Branch();
		branch2.setBranchId(2);
		branch2.setBranchName("Mechanical Engineering");
		branch2.setCollege(college1);

		List<Branch> branches1 = new ArrayList<>(Arrays.asList(branch1, branch2));
		college1.setBranches(branches1);

		when(collegeServiceImpl.getCollegeById(1)).thenReturn(college1);

		this.mockMvc.perform(get("/college/findByCollegeById/{collegeId}", 1))
		.andExpect(status().isOk())
		.andDo(print());

	}

}
