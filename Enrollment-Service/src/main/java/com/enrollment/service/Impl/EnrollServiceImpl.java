package com.enrollment.service.Impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.enrollment.entity.Enroll;
import com.enrollment.exception.EnrollException;
import com.enrollment.feign.CourseClient;
import com.enrollment.feign.CourseClientResponse;
import com.enrollment.feign.StudentClient;
import com.enrollment.feign.StudentClientResponse;
import com.enrollment.repo.EnrollRepository;
import com.enrollment.request.EnrollRequest;
import com.enrollment.response.EnrollResponse;
import com.enrollment.service.IEnrollService;

@Service
public class EnrollServiceImpl implements IEnrollService{

	   private final StudentClient studentClient;
	    private final CourseClient courseClient;
	    private final EnrollRepository enrollRepository;
	    private final ModelMapper modelMapper;

	    public EnrollServiceImpl(
	            StudentClient studentClient,
	            CourseClient courseClient,
	            EnrollRepository enrollRepository,
	            ModelMapper modelMapper
	    ) {
	        this.studentClient = studentClient;
	        this.courseClient = courseClient;
	        this.enrollRepository = enrollRepository;
	        this.modelMapper = modelMapper;
	    }
	    
	    
	    @Override
	    public EnrollResponse enrollStudent(EnrollRequest request) {
	        System.out.println("course id "+request.getCourseId()+" student id "+request.getStudentId());

	        // 1. Fetch student
	        StudentClientResponse student =
	                studentClient.getStudentById(request.getStudentId());

	        if (!"ACTIVE".equalsIgnoreCase(student.getStatus())) {
	            throw new RuntimeException("Student is not active");
	        }

	        // 2. Fetch course
	        CourseClientResponse course =
	                courseClient.getCourseById(request.getCourseId());

	        if (!"ACTIVE".equalsIgnoreCase(course.getStatus())) {
	            throw new RuntimeException("Course is not active");
	        }

	        System.out.println("course id "+request.getCourseId()+" student id "+request.getStudentId());
	        // 3. Map request → entity
	        Enroll enroll = modelMapper.map(request, Enroll.class);
	        enroll.setStatus("ENROLLED"); // not present in request

	        // enrolledAt is handled by @PrePersist

	        Enroll saved = enrollRepository.save(enroll);

	        // 4. Map entity → response
	        EnrollResponse response = modelMapper.map(saved, EnrollResponse.class);

	        // Fix ID serialization
	        response.setEnrollmentId(saved.getId().toString());

	        return response;
	    }

	    
	    @Override
	    public EnrollResponse getEnrollmentById(Long enrollmentId) {

	        Enroll enroll = enrollRepository.findById(enrollmentId)
	                .orElseThrow(() -> new EnrollException("Enrollment not found",HttpStatus.NOT_FOUND));

	        EnrollResponse response = modelMapper.map(enroll, EnrollResponse.class);
	        response.setEnrollmentId(enroll.getId().toString());

	        return response;
	    }
	    
	    @Override
	    public List<EnrollResponse> getEnrollmentsByStudentId(Long studentId) {

	        // optional validation (recommended)
	        studentClient.getStudentById(studentId);

	        List<Enroll> enrollments =
	                enrollRepository.findByStudentId(studentId);

	        return enrollments.stream()
	                .map(enroll -> {
	                    EnrollResponse response =
	                            modelMapper.map(enroll, EnrollResponse.class);
	                    response.setEnrollmentId(enroll.getId().toString());
	                    return response;
	                })
	                .toList();
	    }

	    
	    @Override
	    public List<EnrollResponse> getEnrollmentsByCourseId(Long courseId) {

	        // optional validation
	        courseClient.getCourseById(courseId);

	        List<Enroll> enrollments =
	                enrollRepository.findByCourseId(courseId);

	        return enrollments.stream()
	                .map(enroll -> {
	                    EnrollResponse response =
	                            modelMapper.map(enroll, EnrollResponse.class);
	                    response.setEnrollmentId(enroll.getId().toString());
	                    return response;
	                })
	                .toList();
	    }

	    
	    @Override
	    public EnrollResponse updateEnrollmentStatus(Long enrollmentId, String status) {

	        Enroll enroll = enrollRepository.findById(enrollmentId)
	                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

	        enroll.setStatus(status.toUpperCase());

	        Enroll updated = enrollRepository.save(enroll);

	        EnrollResponse response =
	                modelMapper.map(updated, EnrollResponse.class);

	        response.setEnrollmentId(updated.getId().toString());
	        return response;
	    }

	    @Override
	    public void deleteEnrollment(Long enrollmentId) {

	        Enroll enroll = enrollRepository.findById(enrollmentId)
	                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

	        enrollRepository.delete(enroll);
	    }

}
