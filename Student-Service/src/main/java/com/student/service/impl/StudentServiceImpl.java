package com.student.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.student.entity.Student;
import com.student.entity.StudentStatus;
import com.student.exception.StudentException;
import com.student.repo.StudentRepository;
import com.student.request.StudentRequest;
import com.student.response.StudentResponse;
import com.student.service.IStudentService;


@Service
public class StudentServiceImpl implements IStudentService{

	private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

//    @Override
//    public Student createStudent(Student request) {
//    	
////    	        studentRepository.findByEmail(request.getEmail())
////    	                .ifPresent(s -> {
////    	                    throw new StudentException("Student with email already exists "+request.getEmail(), HttpStatus.CONFLICT);
////    	                });
//    	
////    	        Student student = modelMapper.map(request, Student.class);
////    	        Student student = new Student();
//    	        request.setStatus(StudentStatus.ACTIVE);
//    	
//    	        Student savedStudent = studentRepository.save(request);
////    	        return modelMapper.map(savedStudent, StudentResponse.class);
//    	        return savedStudent;
//    	    }
    
    
    @Override
    public StudentResponse createStudent(StudentRequest request) {

        studentRepository.findByEmail(request.getEmail())
                .ifPresent(s -> {
                    throw new StudentException("Student with email already exists "+request.getEmail(), HttpStatus.CONFLICT);
                });

        Student student = modelMapper.map(request, Student.class);
        student.setStatus(StudentStatus.ACTIVE);

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponse.class);
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentException("Student not found "+studentId, HttpStatus.BAD_REQUEST));

        return modelMapper.map(student, StudentResponse.class);
    }

    @Override
    public StudentResponse getStudentByEmail(String email) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return modelMapper.map(student, StudentResponse.class);
    }

    @Override
    public Page<StudentResponse> getAllStudents(int page, int size) {

        Page<Student> students =
                studentRepository.findAll(PageRequest.of(page, size));

        return students.map(student ->
                modelMapper.map(student, StudentResponse.class)
        );
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest request) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Map request fields onto existing entity
        modelMapper.map(request, student);

        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentResponse.class);
    }

    @Override
    public void deactivateStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setStatus(StudentStatus.INACTIVE);
        studentRepository.save(student);
    }
	
	
}
