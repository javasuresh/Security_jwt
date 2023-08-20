package com.security.app.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
