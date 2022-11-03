package com.binus.OnBoardingApp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.binus.OnBoardingApp.domain.db.RoleUser;

@Repository
public interface RoleUserRepository extends CrudRepository<RoleUser, Long>{

	
	
}
