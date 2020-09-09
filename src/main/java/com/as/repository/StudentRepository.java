package com.as.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
	StudentEntity findByRegisterNumber(String registerNumber);
	StudentEntity findById(int id);
}
