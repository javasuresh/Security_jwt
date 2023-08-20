package com.security.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.paylods.ApiResponse;
import com.security.app.paylods.CommentDto;
import com.security.app.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comment/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable("postId") Integer postId){
		
		CommentDto createCommentDto = this.commentService.createComment(commentDto, postId);
		System.out.println(createCommentDto);
		return new ResponseEntity<>(createCommentDto, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") int categoryId){
		this.commentService.deleteComment(categoryId);
		return new ResponseEntity(new ApiResponse("Comment deleted successfully", true),HttpStatus.OK);
		
	}

}
