package com.security.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.app.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
