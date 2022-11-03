package com.binus.OnBoardingApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.binus.OnBoardingApp.domain.db.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
	
	Transaction save(Transaction transaction);
	
	List<Transaction> findAll();
	
	void deleteById(Long id);
	
	@Query(value = "delete from db_onboarding.transaction ob where ob.course_id=:courseId",
			nativeQuery = true)
	void deleteByCourseId(@Param("courseId") String courseId);
	
	List<Transaction> findByUserId(String userId);
	
	Optional<Transaction> findByCourseId(String courseId);

}
