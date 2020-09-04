package com.as.config;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseResponse {
	private int status;
	private String message;
	private Object result;
	private List<FieldErrorMessage> fieldErrorMessageList;
}