package com.as.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentForm {

	private String register_number;
	private String firstname;
	private String middlename;
	private String lastname;
	private String caste;
	private String birth_place;
	private String date_of_birth;
}
