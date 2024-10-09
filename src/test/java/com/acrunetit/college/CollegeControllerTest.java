package com.acrunetit.college;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.acrunetit.college.controller.CollegeController;
import com.acrunetit.college.entity.Branch;
import com.acrunetit.college.entity.College;
import com.acrunetit.college.serviceimpl.CollegeServiceImpl;

@SpringBootTest
class CollegeControllerTest {

	@Mock
	CollegeServiceImpl collegeServiceImpl;

	@InjectMocks
	CollegeController collegeController;

	@Test
	@Order(1)
	void test_getAllColleges() {
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

		ResponseEntity<List<College>> allColleges = collegeController.getAllColleges();

		assertEquals(HttpStatus.OK, allColleges.getStatusCode());
		assertEquals(collegeList, allColleges.getBody());

	}

	@Test
	@Order(2)
	void test_getCollegeById() {
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

		ResponseEntity<College> collegeById = collegeController.getCollegeById(1);

		assertEquals(HttpStatus.OK, collegeById.getStatusCode());
		assertEquals(college1, collegeById.getBody());
	}

}
