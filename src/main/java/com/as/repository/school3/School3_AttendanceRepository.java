package com.as.repository.school3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.entity.school3.School3_AttendenceEntity;


public interface School3_AttendanceRepository extends JpaRepository<School3_AttendenceEntity, Integer> {
	List<School3_AttendenceEntity> findByRegisterNumber(String registerNumber);
	List<School3_AttendenceEntity> findById(int id);
}
