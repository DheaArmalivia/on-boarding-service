package com.binus.OnBoardingApp.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.dao.RoleUserRepository;
import com.binus.OnBoardingApp.dao.service.RoleUserService;
import com.binus.OnBoardingApp.domain.db.Role;
import com.binus.OnBoardingApp.domain.db.RoleUser;

@Service
public class RoleUserServiceImpl implements RoleUserService {

	@Autowired
	RoleUserRepository dao;
	
	public RoleUserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(RoleUser role) {
		// TODO Auto-generated method stub
		dao.save(role);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

}
