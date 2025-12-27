package com.student.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	  @ExceptionHandler(StudentException.class)
	    public ResponseEntity<Object> handleStudentException(StudentException ex) {
	        return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
	    }
}
