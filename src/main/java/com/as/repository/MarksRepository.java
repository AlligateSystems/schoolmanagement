package com.as.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.entity.MarksEntity;

@Repository
public interface MarksRepository extends JpaRepository<MarksEntity, Integer> {
	List<MarksEntity> findByRegisterNumber(String registerNumber);
	List<MarksEntity> findById(int id);
}
