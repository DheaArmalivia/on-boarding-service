package com.binus.OnBoardingApp.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.domain.db.Course;

@Service
public interface CourseService {
	
	Course save(Course course);
	
	List<Course> findAll();
	
	void deleteById(Long id);
	
	Optional<Course> findById(Long id);
	
	List<Course> findCourseByTrxUserId(String userId);

}
