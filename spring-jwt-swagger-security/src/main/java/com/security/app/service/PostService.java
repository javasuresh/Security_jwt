package com.security.app.service;

import java.util.List;

import com.security.app.entity.Post;
import com.security.app.paylods.PostDto;
import com.security.app.paylods.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto post,Long userId, int catId);
	PostDto updatePost(PostDto post,int postId);
	PostDto getPostById(int postId);
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	void deletePost(int postId);
	
	
	
	List<PostDto> getPostsByCategory(int catId);
	List<PostDto> getPostByUser(Long userId);
	
	List<PostDto> searchPosts(String keyword);
	

}
