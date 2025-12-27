package com.course.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

		@NotBlank(message = "Course name is required")
	    private String courseName;

	    @NotBlank(message = "Course code is required")
	    private String courseCode;

	    private String description;

	    @NotBlank(message = "Status is required")
	    private String status;   // ACTIVE / INACTIVE

	    @Min(value = 1, message = "Duration must be at least 1 hour")
	    private Integer durationInHours;
}
