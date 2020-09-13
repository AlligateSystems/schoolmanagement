package com.as.repository.school2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.entity.school2.School2_AttendenceEntity;


public interface School2_AttendanceRepository extends JpaRepository<School2_AttendenceEntity, Integer> {
	List<School2_AttendenceEntity> findByRegisterNumber(String registerNumber);
	List<School2_AttendenceEntity> findById(int id);
}
