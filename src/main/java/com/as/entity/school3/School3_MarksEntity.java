package com.as.entity.school3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "school3_Marks")
@Getter
@Setter
@ToString
public class School3_MarksEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String rollNumber;
	private String registerNumber;
	private String subject;//
	private String totalTest; //
	private String firstTest; //
	private String secondTest; //
	private String finalTotal;
	private String finalTest;
	private String condonedMarks;
	private String gracePoints;
	
	private String firstTest5;
	private String secondTest5;
	private String notebook;
	private String activity;
	private String totalMarks;
}
