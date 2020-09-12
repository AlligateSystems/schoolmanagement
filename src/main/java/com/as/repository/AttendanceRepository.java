package com.as.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.as.entity.AttendenceEntity;

public interface AttendanceRepository extends JpaRepository<AttendenceEntity, Integer> {
	List<AttendenceEntity> findByRegisterNumber(String registerNumber);
	List<AttendenceEntity> findById(int id);
}
