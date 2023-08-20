package com.security.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.app.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByEmail(String email);
}
