package com.acrunetit.college;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.acrunetit.college.appconstants.CollegeManagementAppConstants;
import com.acrunetit.college.entity.Branch;
import com.acrunetit.college.entity.College;
import com.acrunetit.college.entity.Students;
import com.acrunetit.college.globalexceptionhandle.StudentNotFoundException;
import com.acrunetit.college.repository.StudentRepository;
import com.acrunetit.college.serviceimpl.StudentServiceImpl;

@SpringBootTest
class StudentServiceImplTest {

	@Mock
	StudentRepository studentRepository;

	@InjectMocks
	StudentServiceImpl studentServiceImpl;

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

		when(studentRepository.findAll()).thenReturn(studentsList);

		List<Students> allStudents = studentServiceImpl.getAllStudents();

		assertEquals(2, allStudents.size());

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

		when(studentRepository.findById(1)).thenReturn(Optional.of(student1));

		Students studentById = studentServiceImpl.getStudentById(1);
		assertEquals(student1, studentById);
	}

	@Test
	@Order(3)
	void test_getStudentById_failure() {
		Students student1 = new Students();
		student1.setStudentId(1);

		when(studentRepository.findById(1)).thenReturn(Optional.empty());
		StudentNotFoundException assertThrows2 = assertThrows(StudentNotFoundException.class,
				() -> studentServiceImpl.getStudentById(1));

		assertEquals(CollegeManagementAppConstants.STUDENT_NOT_FOUND + 1, assertThrows2.getMessage());
	}

}
