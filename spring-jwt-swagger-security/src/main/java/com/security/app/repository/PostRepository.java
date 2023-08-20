package com.security.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.app.entity.Category;
import com.security.app.entity.Post;
import com.security.app.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
}
