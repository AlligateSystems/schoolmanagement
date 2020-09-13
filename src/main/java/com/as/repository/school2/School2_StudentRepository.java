package com.as.repository.school2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.entity.school2.School2_StudentEntity;


@Repository
public interface School2_StudentRepository extends JpaRepository<School2_StudentEntity, Integer> {
	School2_StudentEntity findByRegisterNumber(String registerNumber);
	School2_StudentEntity findById(int id);
}
