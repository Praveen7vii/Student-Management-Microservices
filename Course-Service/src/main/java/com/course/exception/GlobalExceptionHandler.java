package com.course.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CourseException.class)
	public ResponseEntity<Map<String, Object>> handleCourseException(CourseException courseException){
		Map<String,Object> errorResponse = new HashMap<>();
		errorResponse.put("status", "failure");
		errorResponse.put("type","Course Exception");
		errorResponse.put("error", courseException.getMessage());
		errorResponse.put("localTime", LocalDateTime.now());
		errorResponse.put("status", courseException.getHttpStatus().toString());
		return new ResponseEntity<Map<String,Object>>(errorResponse, courseException.getHttpStatus());
	}
	
}
