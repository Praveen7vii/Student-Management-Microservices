package com.enrollment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Student-Service")
public interface StudentClient {

	 @GetMapping("/api/student/internal/{studentId}")
	 StudentClientResponse getStudentById(@PathVariable Long studentId);
}
