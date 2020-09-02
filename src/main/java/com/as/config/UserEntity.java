package com.as.config;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class UserEntity extends Base {
	private String username;
	private String password;
	private String email;
	private String mobile;
}
