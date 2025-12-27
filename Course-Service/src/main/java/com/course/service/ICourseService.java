package com.course.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.course.request.CourseRequest;
import com.course.response.CourseResponse;

public interface ICourseService {

	CourseResponse createCourse(CourseRequest requestDto);

	CourseResponse getCourseById(Long id);

	List<CourseResponse> getAllCourses();

	CourseResponse updateCourse(Long id, CourseRequest requestDto);

	void deleteCourse(Long id);
}
