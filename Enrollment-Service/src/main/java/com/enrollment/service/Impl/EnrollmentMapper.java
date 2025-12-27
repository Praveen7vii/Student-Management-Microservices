package com.enrollment.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enrollment.entity.Enroll;
import com.enrollment.request.EnrollRequest;
import com.enrollment.response.EnrollResponse;

@Component
public class EnrollmentMapper {

	@Autowired
	ModelMapper modelMapper;
	
	   public Enroll toEntity(EnrollRequest request) {
	        Enroll enrollment = modelMapper.map(request, Enroll.class);
	        enrollment.setStatus("ENROLLED"); // business default
	        return enrollment;
	    }

	    public EnrollResponse toResponse(Enroll enrollment) {
	        return modelMapper.map(enrollment, EnrollResponse.class);
	    }
}
