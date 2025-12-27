package com.enrollment.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollResponse {
	
    @JsonSerialize(using = ToStringSerializer.class)
	private String enrollmentId;
    private Long studentId;
    private Long courseId;
    private String status;
    private LocalDateTime enrolledAt;
}
