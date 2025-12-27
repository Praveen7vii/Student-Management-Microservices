package com.enrollment.feign;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClientResponse {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String address;
    private String status; 
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
