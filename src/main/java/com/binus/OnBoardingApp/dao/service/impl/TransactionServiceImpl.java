package com.binus.OnBoardingApp.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.dao.TransactionRepository;
import com.binus.OnBoardingApp.dao.service.TransactionService;
import com.binus.OnBoardingApp.domain.db.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository dao;
	
	public TransactionServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Transaction save(Transaction trx) {
		// TODO Auto-generated method stub
		return dao.save(trx);
	}

	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public void deleteByCourseId(String courseId) {
		// TODO Auto-generated method stub
		dao.deleteByCourseId(courseId);
	}

	@Override
	public List<Transaction> findByUserId(String userId) {
		// TODO Auto-generated method stub
		return dao.findByUserId(userId);
	}

	@Override
	public Optional<Transaction> findByCourseId(String courseId) {
		// TODO Auto-generated method stub
		return dao.findByCourseId(courseId);
	}

}
