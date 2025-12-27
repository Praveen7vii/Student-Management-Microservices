package com.course.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.entity.Course;

public interface CourseRepositry extends JpaRepository<Course, Long>{

}
