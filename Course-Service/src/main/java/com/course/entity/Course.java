package com.course.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String courseName;

	    @Column(nullable = false, unique = true)
	    private String courseCode;

	    private String description;

	    @Column(nullable = false)
	    private String status;   // ACTIVE / INACTIVE

	    @Column(nullable = false)
	    private Integer durationInHours;

	    @Column(updatable = false)
	    private LocalDateTime createdAt;
	    
	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = LocalDateTime.now();
	    }
}
