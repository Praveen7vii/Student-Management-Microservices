package com.course.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

	 	private String id;   // String to avoid JS precision issue

	    private String courseName;

	    private String courseCode;

	    private String description;

	    private String status;

	    private Integer durationInHours;

	    private LocalDateTime createdAt;
}
