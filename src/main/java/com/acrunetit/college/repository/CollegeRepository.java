package com.acrunetit.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acrunetit.college.entity.College;

public interface CollegeRepository extends JpaRepository<College,Integer> {

}
