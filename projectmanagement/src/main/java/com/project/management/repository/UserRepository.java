package com.project.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.management.model.User;

@Repository
public interface UserRepository extends JpaRepository<Long, User> {
	
	User findByEmail(String email);
}
