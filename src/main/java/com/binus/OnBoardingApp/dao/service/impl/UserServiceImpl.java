package com.binus.OnBoardingApp.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.dao.UserRepository;
import com.binus.OnBoardingApp.dao.service.UserService;
import com.binus.OnBoardingApp.domain.db.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository dao;
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<User> findByUsernameAndPasswordAndActive(String username, String password, Boolean active) {
		// TODO Auto-generated method stub
		return dao.findByUsernameAndPasswordAndActive(username, password, active);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return dao.save(user);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
