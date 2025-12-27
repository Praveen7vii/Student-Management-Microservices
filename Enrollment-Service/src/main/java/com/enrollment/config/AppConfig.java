package com.enrollment.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.enrollment.entity.Enroll;
import com.enrollment.request.EnrollRequest;
import com.enrollment.response.EnrollResponse;

@Configuration
public class AppConfig {

	@Bean
    public ModelMapper modelMapper() {
	    ModelMapper modelMapper = new ModelMapper();

        // Optional but recommended
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // Entity → Response
        modelMapper.typeMap(Enroll.class, EnrollResponse.class)
                .addMapping(Enroll::getId, EnrollResponse::setEnrollmentId);

        // Request → Entity (ADD THIS PART)
        modelMapper.typeMap(EnrollRequest.class, Enroll.class)
                .addMappings(mapper -> {
                    mapper.map(EnrollRequest::getStudentId, Enroll::setStudentId);
                    mapper.map(EnrollRequest::getCourseId, Enroll::setCourseId);
                    mapper.skip(Enroll::setId);
                });

        return modelMapper;
    }
}
