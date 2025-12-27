package com.enrollment.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enrollment.request.EnrollRequest;
import com.enrollment.response.EnrollResponse;
import com.enrollment.service.IEnrollService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
	
	private final IEnrollService enrollmentService;

	public EnrollmentController(IEnrollService enrollmentService) {
		super();
		this.enrollmentService = enrollmentService;
	}
	
	
	@PostMapping("/enroll")
	public ResponseEntity<EnrollResponse> enrollStudent(
	        @Valid @RequestBody EnrollRequest request
	) {
	    return ResponseEntity.ok(enrollmentService.enrollStudent(request));
	}
	
	@GetMapping("/student/{studentId}")
	public List<EnrollResponse> getByStudent(@PathVariable Long studentId) {
	    return enrollmentService.getEnrollmentsByStudentId(studentId);
	}

	@GetMapping("/course/{courseId}")
	public List<EnrollResponse> getByCourse(@PathVariable Long courseId) {
	    return enrollmentService.getEnrollmentsByCourseId(courseId);
	}

	@PatchMapping("/update/{id}/status")
	public EnrollResponse updateStatus(
	        @PathVariable Long id,
	        @RequestParam String status
	) {
	    return enrollmentService.updateEnrollmentStatus(id, status);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok("The record has been deleted successfully");

	}
}
