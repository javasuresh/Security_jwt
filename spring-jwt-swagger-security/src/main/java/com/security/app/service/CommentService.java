package com.security.app.service;

import com.security.app.paylods.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto comm, Integer postId);

	void deleteComment(Integer commentId);
}
