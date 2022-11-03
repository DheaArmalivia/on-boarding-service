package com.binus.OnBoardingApp.controller.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.dao.service.CourseService;
import com.binus.OnBoardingApp.dao.service.TransactionService;
import com.binus.OnBoardingApp.dao.service.UserService;
import com.binus.OnBoardingApp.domain.db.Course;
import com.binus.OnBoardingApp.domain.db.Transaction;
import com.binus.OnBoardingApp.domain.db.User;
import com.binus.OnBoardingApp.domain.response.GeneralResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class CourseControllerService {
	
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	CourseService courseService;
	
	@Autowired
	TransactionService trxService;
	
	@Autowired
	UserService userService;
	
	public CourseControllerService() {
		// TODO Auto-generated constructor stub
	}
	
	public Course saveCourse(Course course) {
		course.setModifiedDate(getTimestamp());
		course = courseService.save(course);
		return course;
	}
	
	public String saveTrxCourse(String req) throws JsonMappingException, JsonProcessingException {
//	public Course saveTrxCourse(String req) throws JsonMappingException, JsonProcessingException {
		JsonNode node = mapper.readTree(req);
		GeneralResponse genResp = new GeneralResponse();
		Course course = new Course();
		if(node.has("title") && node.has("content")) {
			
			course.setTitle(node.get("title").asText());
			course.setContent(node.get("content").asText());
			course.setStatus(node.has("status") ? node.get("status").asInt()
					: 0);
			course = courseService.save(course);
			genResp.setError("false");
			genResp.setMessage("Successfully add course");
			
			if(node.has("username")) {
				Transaction trx = new Transaction();
				Optional<User> exUser = userService.findByUsername(node.get("username").asText());
				trx.setCourseId(String.valueOf(course.getId()));
				Date dt = new Date();
				trx.setGenId(getGenId());
				trx.setUserId(exUser.isPresent() ? String.valueOf(exUser.get().getId()) : "");
				trx = trxService.save(trx);
				genResp.setError("false");
				genResp.setMessage("Successfully add course");
			}
			
		} else {
			genResp.setError("true");
			genResp.setMessage("Failed to add course, title and content is null");
		}
		
		ObjectNode result = mapper.createObjectNode();
		result.set("course", mapper.convertValue(course, JsonNode.class));
		result.set("result", mapper.convertValue(genResp, JsonNode.class));
		
		return result.toPrettyString();
//		return course;
	}
	
	public List<Course> getAllCourse() {
		return courseService.findAll();
	}

	public GeneralResponse deleteCourse(Long courseId) {
		String cId = String.valueOf(courseId);
		Optional<Transaction> trx = trxService.findByCourseId(cId);
		if(trx.isPresent()) {
			trxService.deleteById(trx.get().getId());
		}
		System.out.println("course id " + courseId + " " + cId);
//		trxService.deleteByCourseId(cId);
		courseService.deleteById(courseId);
		GeneralResponse genResp = new GeneralResponse();
		genResp.setError("false");
		genResp.setMessage("Successfully delete course with id " + courseId);
		return genResp;
	}
	
	public List<Course> getCourseByTrxUserId(String userId) {
		Optional<User> exUser = userService.findByUsername(userId);
		return courseService.findCourseByTrxUserId(String.valueOf(exUser.get().getId()));
	}
	
	public String findById(Long id) {
//	public Course findById(Long id) {
		Course result = new Course();
		GeneralResponse genResp = new GeneralResponse();
		Optional<Course> course = courseService.findById(id);
		if(course.isPresent()) {
			result = course.get();
		}
		ObjectNode node = mapper.createObjectNode();
		node.set("course", mapper.convertValue(result, JsonNode.class));
		node.set("result", mapper.convertValue(genResp, JsonNode.class));
		return node.toPrettyString();
//		return result;
	}
	
	public String getGenId() {
		String genId = "";
		genId = getTimestamp().toString().replace("-", "").replace(":", "").
				replace(" ", "").replace(".", "");
		if(genId.length() > 16) {
			genId = genId.substring(0, 16);
		}
		return genId; 
	}
	
	public Timestamp getTimestamp() {
		Date dt = new Date();
		return new Timestamp(dt.getTime());
	}

}
