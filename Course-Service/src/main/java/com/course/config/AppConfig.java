package com.course.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.course.entity.Course;
import com.course.response.CourseResponse;

@Configuration
public class AppConfig {
	  	
		@Bean
	    public ModelMapper modelMapper() {
			ModelMapper modelMapper = new ModelMapper();
			 modelMapper.typeMap(Course.class, CourseResponse.class)
             .addMappings(mapper ->
                     mapper.map(
                             src -> src.getId().toString(),
                             CourseResponse::setId
                     )
             );			return modelMapper;
		}
}
