package com.as.repository.school1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.entity.school1.School1_AttendenceEntity;


public interface School1_AttendanceRepository extends JpaRepository<School1_AttendenceEntity, Integer> {
	List<School1_AttendenceEntity> findByRegisterNumber(String registerNumber);
	List<School1_AttendenceEntity> findById(int id);
}
