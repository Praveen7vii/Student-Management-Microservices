package com.student.rest;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.exception.StudentException;
import com.student.request.StudentRequest;
import com.student.response.StudentResponse;
import com.student.service.IStudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student/")
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@PostMapping("/create")
	ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest req, BindingResult bind){
		System.out.println(req);
		
		if(bind.hasErrors()) {
			throw new StudentException("Invalid Input", HttpStatus.BAD_REQUEST);
		}
		
		if (req.getEmail() == null || req.getEmail().isBlank()) {
		    throw new StudentException("Email cannot be null",
		            HttpStatus.BAD_REQUEST);
		}
		
		StudentResponse response = studentService.createStudent(req);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}

	
	@GetMapping("/get/{id}")
	ResponseEntity<StudentResponse> getStudent(@PathVariable Long id){
		StudentResponse stdres = studentService.getStudentById(id);
		
//		return new ResponseEntity<>(stdres,HttpStatus.OK);
		return ResponseEntity.ok(stdres);
	}
	
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponse> getStudentByEmail(
            @PathVariable String email) {

        StudentResponse response = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/all")
    public ResponseEntity<Page<StudentResponse>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<StudentResponse> students =
                studentService.getAllStudents(page, size);

        return ResponseEntity.ok(students);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request, BindingResult bind) {
    	
    	if(bind.hasErrors()) {
			throw new StudentException("Invalid Input", HttpStatus.BAD_REQUEST);
		}

        StudentResponse response =
                studentService.updateStudent(id, request);

        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deactivateStudent(
            @PathVariable Long id) {

        studentService.deactivateStudent(id);
        return ResponseEntity.ok("Student deactivated successfully");
    }
    
    @GetMapping("/internal/{studentId}")
    public ResponseEntity<StudentResponse> getStudentInternal(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }
}
