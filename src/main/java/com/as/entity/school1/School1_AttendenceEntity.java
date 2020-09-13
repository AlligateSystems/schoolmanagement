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
@Table(name = "school1_Attendence")
@Getter
@Setter
@ToString
public class School1_AttendenceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String rollNumber;
	private String registerNumber;
	private String month;
	private String present;
	private String total;
}
