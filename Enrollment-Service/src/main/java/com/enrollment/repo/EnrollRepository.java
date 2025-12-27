package com.enrollment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enrollment.entity.Enroll;

public interface EnrollRepository extends JpaRepository<Enroll, Long>{

	boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

	List<Enroll> findByStudentId(Long studentId);

	List<Enroll> findByCourseId(Long courseId);

}
