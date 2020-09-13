package com.as.repository.school3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.entity.school3.School3_StudentEntity;


@Repository
public interface School3_StudentRepository extends JpaRepository<School3_StudentEntity, Integer> {
	School3_StudentEntity findByRegisterNumber(String registerNumber);
	School3_StudentEntity findById(int id);
}
