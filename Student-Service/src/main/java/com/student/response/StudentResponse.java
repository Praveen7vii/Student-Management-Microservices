package com.student.response;

import java.time.LocalDateTime;

import com.student.entity.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
	private Long id;
    private String name;
    private String email;
    private Integer age;
    private String address;
    private StudentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
