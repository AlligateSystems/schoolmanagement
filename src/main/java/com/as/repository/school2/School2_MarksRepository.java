package com.as.repository.school2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.entity.school2.School2_MarksEntity;


@Repository
public interface School2_MarksRepository extends JpaRepository<School2_MarksEntity, Integer> {
	List<School2_MarksEntity> findByRegisterNumber(String registerNumber);
	List<School2_MarksEntity> findById(int id);
}
