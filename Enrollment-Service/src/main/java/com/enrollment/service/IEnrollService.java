package com.enrollment.service;

import java.util.List;

import com.enrollment.request.EnrollRequest;
import com.enrollment.response.EnrollResponse;

public interface IEnrollService {   
	
	EnrollResponse enrollStudent(EnrollRequest request);

	EnrollResponse getEnrollmentById(Long enrollmentId);

	List<EnrollResponse> getEnrollmentsByStudentId(Long studentId);

	List<EnrollResponse> getEnrollmentsByCourseId(Long courseId);

	EnrollResponse updateEnrollmentStatus(Long enrollmentId, String status);

	void deleteEnrollment(Long enrollmentId);
	
	
	
//	EnrollResponse enrollStudent(EnrollRequest request);
//
//	List<EnrollResponse> getEnrollmentsByStudentId(Long studentId);
//
//	List<EnrollResponse> getEnrollmentsByCourseId(Long courseId);
//
//	EnrollResponse updateEnrollmentStatus(Long enrollmentId, String status);
//
//	void cancelEnrollment(Long enrollmentId);
//
//	EnrollResponse getEnrollmentById(Long enrollmentId);
}
