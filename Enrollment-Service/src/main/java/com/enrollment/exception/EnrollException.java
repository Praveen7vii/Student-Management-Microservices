package com.enrollment.exception;

import org.springframework.http.HttpStatus;

public class EnrollException extends RuntimeException{

	HttpStatus httpStatus;

	public EnrollException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	
}
