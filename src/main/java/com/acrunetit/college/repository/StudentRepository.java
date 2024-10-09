package com.acrunetit.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acrunetit.college.entity.Students;

public interface StudentRepository  extends JpaRepository<Students,Integer> {

}
