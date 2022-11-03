package com.binus.OnBoardingApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.binus.OnBoardingApp.domain.db.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
	
	Course save(Course course);
	
	List<Course> findAll();
	
	void deleteById(Long id);
	
	Optional<Course> findById(Long id);
	
	@Query(value="select * from db_onboarding.course oc where oc.id in "
			+ "(select course_id from db_onboarding.transaction tx "
			+ "where tx.user_id=:userId)", nativeQuery = true)
	List<Course> findAllCourseTrxByUserId(@Param("userId") String userId);

}
