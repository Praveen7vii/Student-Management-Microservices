package com.enrollment.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EnrollException.class)
	public ResponseEntity<Map<String,Object>> handleEnrollException(EnrollException enrollException){
		Map<String,Object> errorResponse = new HashMap<>();
		errorResponse.put("status", "failure");
		errorResponse.put("type","Enrollment Exception");
		errorResponse.put("error", enrollException.getMessage());
		errorResponse.put("localTime", LocalDateTime.now());
		errorResponse.put("status", enrollException.getHttpStatus().toString());
		return new ResponseEntity<Map<String,Object>>(errorResponse, enrollException.getHttpStatus());
	}
}
