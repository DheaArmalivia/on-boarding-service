package com.binus.OnBoardingApp.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.binus.OnBoardingApp.controller.service.CourseControllerService;
import com.binus.OnBoardingApp.domain.db.Course;
import com.binus.OnBoardingApp.domain.db.User;
import com.binus.OnBoardingApp.domain.response.GeneralResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/rest/api")
public class CourseController {
	
	@Autowired
	CourseControllerService service;

	public CourseController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/getAllCourses", method = RequestMethod.GET)
	public List<Course> getUser() {
		return service.getAllCourse();
	}
	
	@RequestMapping(value = "/getCourseById", method = RequestMethod.POST)
	public String addUser(@RequestBody Course req) {
//	public Course addUser(@RequestBody Course req) {
		return service.findById(req.getId());
	}
	
	@RequestMapping(value = "/getCoursesByUser", method = RequestMethod.POST)
	public List<Course> findCourse(@RequestBody User req) {
		return service.getCourseByTrxUserId(req.getUsername());
	}
	
	@RequestMapping(value = "/createCourse", method = RequestMethod.POST)
	public String addCourse(@RequestBody String reqMsg) throws JsonMappingException, JsonProcessingException {
//	public Course addCourse(@RequestBody String reqMsg) throws JsonMappingException, JsonProcessingException {
		return service.saveTrxCourse(reqMsg);
	}
	
	@RequestMapping(value = "/updateCourse", method = RequestMethod.PUT)
	public Course updateCourse(@RequestBody Course req) {
		return service.saveCourse(req);
	}
	
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.DELETE)
	public GeneralResponse deleteCourse(@RequestBody Course req) {
		return service.deleteCourse(req.getId());
	}
	
	@RequestMapping(value = "/genId", method = RequestMethod.POST)
	public String deleteCourse(@RequestBody String reqMsg) {
		Date dt = new Date();
		Timestamp ts = new Timestamp(dt.getTime());
		String gen = ts.toString().replace("-", "").replace(":", "").
				replace(" ", "").replace(".", "");
		System.out.println(gen.length());
		if(gen.length() > 16) {
			gen = gen.substring(0, 16);
		}
		System.out.println(gen);
		System.out.println(gen.length());
		return "addUser";
	}

}
