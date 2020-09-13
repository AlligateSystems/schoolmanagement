package com.as.repository.school1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.entity.school1.School1_StudentEntity;


@Repository
public interface School1_StudentRepository extends JpaRepository<School1_StudentEntity, Integer> {
	School1_StudentEntity findByRegisterNumber(String registerNumber);
	School1_StudentEntity findById(int id);
}
