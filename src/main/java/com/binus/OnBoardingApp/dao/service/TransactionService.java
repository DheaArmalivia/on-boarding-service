package com.binus.OnBoardingApp.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.domain.db.Transaction;

@Service
public interface TransactionService {
	
	Transaction save(Transaction trx);
	
	List<Transaction> findAll();
	
	void deleteById(Long id);
	
	void deleteByCourseId(String courseId);
	
	List<Transaction> findByUserId(String userId);
	
	Optional<Transaction> findByCourseId(String courseId);

}
