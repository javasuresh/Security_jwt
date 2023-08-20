package com.security.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.app.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
