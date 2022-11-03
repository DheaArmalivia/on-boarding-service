package com.binus.OnBoardingApp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binus.OnBoardingApp.domain.db.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsernameAndPasswordAndActive(String username, String password, Boolean active);
	
	Optional<User> findByUsername(String username);
	
	User save(User user);

}
