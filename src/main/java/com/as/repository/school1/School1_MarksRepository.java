package com.as.repository.school1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.entity.school1.School1_MarksEntity;


@Repository
public interface School1_MarksRepository extends JpaRepository<School1_MarksEntity, Integer> {
	List<School1_MarksEntity> findByRegisterNumber(String registerNumber);
	List<School1_MarksEntity> findById(int id);
}
