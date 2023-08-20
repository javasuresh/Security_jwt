package com.security.app.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.app.entity.Comment;
import com.security.app.entity.Post;
import com.security.app.exception.ResourceNotFoundException;
import com.security.app.paylods.CommentDto;
import com.security.app.paylods.PostResponse;
import com.security.app.repository.CommentRepository;
import com.security.app.repository.PostRepository;
import com.security.app.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CommentDto createComment(CommentDto commDto, Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		Comment comment = this.mapper.map(commDto, Comment.class);
		comment.setPost(post);
		Comment saveComment= this.commentRepository.save(comment);
		return this.mapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment con=this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
		
		this.commentRepository.delete(con);
	}

}
