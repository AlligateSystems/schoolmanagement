package com.as.config;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
public class StudentEntity extends Base {

	private String registernumber;
	private String firstname;
	private String middlename;
	private String lastname;
	private String caste;
	private String birthplace;
	private String dateofbirth;
}