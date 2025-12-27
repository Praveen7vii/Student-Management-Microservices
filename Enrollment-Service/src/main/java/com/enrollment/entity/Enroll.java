package com.enrollment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "enrollments",
uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
//uniqueConstraints prevents duplicate enrollments for the same student and course.
@AllArgsConstructor
@NoArgsConstructor
public class Enroll {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "student_id", nullable = false)
	    private Long studentId;

	    @Column(name = "course_id", nullable = false)
	    private Long courseId;

	    @Column(nullable = false)
	    private String status; 
	    // ENROLLED, COMPLETED, DROPPED

	    @Column(name = "enrolled_at", nullable = false)
	    private LocalDateTime enrolledAt;
	    
//	    The @PrePersist annotation is a lifecycle callback that marks a method to be executed
//	    just before an entity is persisted (inserted) into the database for the first time.
	    @PrePersist
	    protected void onCreate() {
	        this.enrolledAt = LocalDateTime.now();
	    }
	    
//	    How It Works
//	    You create a new entity instance.
//	    You call repository.save(entity).
//	    Before the SQL INSERT is executed, JPA calls all methods annotated with @PrePersist.
//	    The entity is then inserted into the database.

}
