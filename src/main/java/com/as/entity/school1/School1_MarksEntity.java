package com.as.entity.school1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "school1_Marks")
@Getter
@Setter
@ToString
public class School1_MarksEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String rollNumber;
	private String registerNumber;
	private String subject;//
	
	private String total;
	private String obtainedMarks;
	private String obtainedGrade;
	private String note;

}
