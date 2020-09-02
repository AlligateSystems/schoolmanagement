package com.as.config;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
	private int status;
	private String message;
	private Object result;
	private List<FieldErrorMessage> fieldErrorMessageList;
}