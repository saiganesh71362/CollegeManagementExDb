package com.acrunetit.college;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.acrunetit.college.entity.Branch;
import com.acrunetit.college.entity.College;
import com.acrunetit.college.entity.Students;
import com.acrunetit.college.globalexceptionhandle.BranchNotFoundException;
import com.acrunetit.college.repository.BranchRepository;
import com.acrunetit.college.serviceimpl.BranchServiceImpl;

@SpringBootTest
class BranchServiceImplTest {

	@Mock
	BranchRepository branchRepository;

	@InjectMocks
	BranchServiceImpl branchServiceImpl;

	@Test
	@Order(1)
	void test_getAllBranches() {
		
		ArrayList<Branch> branchList = new ArrayList<>();

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
		student1.setBranch(branch1);

		Students student2 = new Students();
		student2.setStudentId(2);
		student2.setStudentName("Bob Smith");
		student2.setBranch(branch1);

		List<Students> students1 = new ArrayList<>(Arrays.asList(student1, student2));
		branch1.setStudents(students1);

		College college2 = new College();
		college2.setCollegeId(2);
		college2.setCollegeName("Sunrise Institute of Technology");
		college2.setCollegeAddress("456 Sunrise Blvd, Sun City");
		college2.setCollegeContact("987-654-3210");

		Branch branch2 = new Branch();
		branch2.setBranchId(2);
		branch2.setBranchName("Mechanical Engineering");
		branch2.setCollege(college2);

		Students student11 = new Students();
		student11.setStudentId(3);
		student11.setStudentName("Charlie Brown");
		student11.setBranch(branch2);

		Students student22 = new Students();
		student22.setStudentId(4);
		student22.setStudentName("David Green");
		student22.setBranch(branch2);

		List<Students> students2 = Arrays.asList(student11, student22);
		branch2.setStudents(students2);

		branchList.add(branch1);
		branchList.add(branch2);

		when(branchRepository.findAll()).thenReturn(branchList);

		List<Branch> allBranches = branchServiceImpl.getAllBranches();
		assertEquals(2, allBranches.size());

	}

	@Test
	@Order(2)
	void test_getBranchById() {

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
		student1.setBranch(branch1);

		Students student2 = new Students();
		student2.setStudentId(2);
		student2.setStudentName("Bob Smith");
		student2.setBranch(branch1);

		List<Students> students1 = new ArrayList<>(Arrays.asList(student1, student2));
		branch1.setStudents(students1);

		when(branchRepository.findById(1)).thenReturn(Optional.of(branch1));

		Branch branchById = branchServiceImpl.getBranchById(1);

		assertEquals(branch1, branchById);

	}

	@Test
	@Order(3)
	void test_getBranchById_failure() {
		Branch branch1 = new Branch();
		branch1.setBranchId(1);
		when(branchRepository.findById(1)).thenReturn(Optional.empty());

		BranchNotFoundException assertThrows2 = assertThrows(BranchNotFoundException.class,
				() -> branchServiceImpl.getBranchById(1));

		assertEquals("Branch Not Found :" + 1, assertThrows2.getMessage());
	}

}
