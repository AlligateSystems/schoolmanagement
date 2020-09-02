package com.as.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserForm {
	private int id;
	private String username;
	@JsonIgnore
	private String password;
	private String email;
	private String mobile;
}
