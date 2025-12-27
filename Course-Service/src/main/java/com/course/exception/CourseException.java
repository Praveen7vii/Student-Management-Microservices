package com.course.exception;

import org.springframework.http.HttpStatus;

public class CourseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpStatus httpStatus;

	public CourseException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus !=null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	
}
