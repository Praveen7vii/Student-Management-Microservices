package com.enrollment.feign;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseClientResponse {

    private String id;
    private String courseName;
    private String courseCode;
    private String description;
    private String status;
    private Integer durationInHours;
    private LocalDateTime createdAt;
}
