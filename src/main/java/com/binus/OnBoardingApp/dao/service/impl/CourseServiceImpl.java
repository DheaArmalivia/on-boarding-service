package com.binus.OnBoardingApp.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.dao.CourseRepository;
import com.binus.OnBoardingApp.dao.service.CourseService;
import com.binus.OnBoardingApp.domain.db.Course;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseRepository dao;

	public CourseServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Course save(Course course) {
		// TODO Auto-generated method stub
		return dao.save(course);
	}

	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public Optional<Course> findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Course> findCourseByTrxUserId(String userId) {
		// TODO Auto-generated method stub
		return dao.findAllCourseTrxByUserId(userId);
	}

}
