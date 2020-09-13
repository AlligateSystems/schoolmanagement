package com.as.entity.school1;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.as.config.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "school1_student")
@Getter
@Setter
@ToString
public class School1_StudentEntity extends BaseEntity {
	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private String gender;
	private String birthPlace;
	private String dateOfBirth;
	private String fatherName;
	private String motherName;
	private String occupation;
	private String caste;
	private String address;
	private String contactNumber;
	private String email;
	private String registerNumber;
	private String classIn;
	private String rollNumber;
	private String bloodGroup;
}
