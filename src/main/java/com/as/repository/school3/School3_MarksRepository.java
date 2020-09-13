package com.as.repository.school3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.entity.school3.School3_MarksEntity;


@Repository
public interface School3_MarksRepository extends JpaRepository<School3_MarksEntity, Integer> {
	List<School3_MarksEntity> findByRegisterNumber(String registerNumber);
	List<School3_MarksEntity> findById(int id);
}
