package com.as.config;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JsonIgnore
	private Date createdDate;
	
	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	private Date updatedDate;
	
	@JsonIgnore
	private String updatedBy;
	
	@JsonIgnore
	private boolean Enable;
}
