package com.binus.OnBoardingApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.binus.OnBoardingApp.domain.db.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	
	@Query(value = "select * from db_onboarding.role r where r.role=:role", 
			nativeQuery=true)
	Optional<Role> findbyRole(@Param("role") String role);
	
	List<Role> findAll();

}
