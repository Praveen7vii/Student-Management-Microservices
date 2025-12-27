package com.enrollment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Course-Service")
public interface CourseClient {
	
	  @GetMapping ("/api/course/internal/{courseId}")
	   CourseClientResponse getCourseById(@PathVariable Long courseId);
}
