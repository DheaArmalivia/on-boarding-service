package com.binus.OnBoardingApp.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.dao.RoleRepository;
import com.binus.OnBoardingApp.dao.service.RoleService;
import com.binus.OnBoardingApp.domain.db.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository dao;
	
	public RoleServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Optional<Role> findByRole(String role) {
		// TODO Auto-generated method stub
		return dao.findbyRole(role);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return dao.save(role);
	}

}
