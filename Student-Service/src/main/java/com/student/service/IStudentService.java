package com.student.service;


import org.springframework.data.domain.Page;

import com.student.entity.Student;
import com.student.request.StudentRequest;
import com.student.response.StudentResponse;

public interface IStudentService {

	    StudentResponse createStudent(StudentRequest request);
//		Student createStudent(Student request);
	
	    StudentResponse getStudentById(Long studentId);

	    StudentResponse getStudentByEmail(String email);

	    Page<StudentResponse> getAllStudents(int page, int size);

	    StudentResponse updateStudent(Long studentId, StudentRequest request);

	    void deactivateStudent(Long studentId);
	    
	    
	
}

	
