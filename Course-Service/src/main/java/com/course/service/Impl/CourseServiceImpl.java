package com.course.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.entity.Course;
import com.course.exception.CourseException;
import com.course.repo.CourseRepositry;
import com.course.request.CourseRequest;
import com.course.response.CourseResponse;
import com.course.service.ICourseService;

import jakarta.validation.Valid;

@Service
public class CourseServiceImpl implements ICourseService{
	
	@Autowired
	CourseRepositry courseRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CourseResponse createCourse(@Valid CourseRequest requestDto) {
		Course course = modelMapper.map(requestDto, Course.class);
		course = courseRepository.save(course);
		CourseResponse response = modelMapper.map(course, CourseResponse.class);
	    response.setId(course.getId().toString()); // important for JS safety

		return response;
	}

	  @Override
	    public CourseResponse getCourseById(Long id) {
	        Course course = courseRepository.findById(id)
	                .orElseThrow(() -> new CourseException("Course not found with id: " + id, HttpStatus.NOT_FOUND));

	        return modelMapper.map(course, CourseResponse.class);
	    }

	  @Override
	    public List<CourseResponse> getAllCourses() {
	        return courseRepository.findAll()
	                .stream()
	                .map(course -> modelMapper.map(course, CourseResponse.class))
	                .collect(Collectors.toList());
	    }

	  @Override
	    public CourseResponse updateCourse(Long id, CourseRequest requestDto) {
	        Course existingCourse = courseRepository.findById(id)
	                .orElseThrow(() -> new CourseException("Course not found with id: " + id, HttpStatus.NOT_FOUND));

	        modelMapper.map(requestDto, existingCourse);
	        existingCourse = courseRepository.save(existingCourse);

	        return modelMapper.map(existingCourse, CourseResponse.class);
	    }

	  @Override
	    public void deleteCourse(Long id) {
	        Course course = courseRepository.findById(id)
	                .orElseThrow(() -> new CourseException("Course not found with id: " + id, HttpStatus.NOT_FOUND));

	        courseRepository.delete(course);
	    }

}
