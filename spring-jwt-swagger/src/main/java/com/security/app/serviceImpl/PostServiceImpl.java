package com.security.app.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.app.entity.Category;
import com.security.app.entity.Post;
import com.security.app.entity.User;
import com.security.app.exception.ResourceNotFoundException;
import com.security.app.paylods.PostDto;
import com.security.app.paylods.PostResponse;
import com.security.app.repository.CategoryRepository;
import com.security.app.repository.PostRepository;
import com.security.app.repository.UserRepository;
import com.security.app.service.PostService;

import org.springframework.data.domain.*;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public PostDto createPost(PostDto post,Long userId, int catId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		Category category=this.categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", catId));
		
		Post posts = this.mapper.map(post, Post.class);
		posts.setImageName("default.png");
		posts.setAddDate(new Date());
		posts.setUser(user);
		posts.setCategory(category);
		Post save = this.postRepository.save(posts);
		
		return this.mapper.map(save, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post save = this.postRepository.save(post);
		return this.mapper.map(save, PostDto.class);
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort=null;
		Sort sort2=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepository.findAll(p);
		List<Post> allPosts=pagePost.getContent();
		List<PostDto> postDto =allPosts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse pp=new PostResponse();
		pp.setContent(postDto);
		pp.setPageNumber(pagePost.getNumber());
		pp.setPageSize(pagePost.getSize());
		pp.setTotalElements(pagePost.getTotalElements());
		pp.setTotalPage(pagePost.getTotalPages());
		pp.setLastPage(pagePost.isLast());
		return pp;
	}

	@Override
	public void deletePost(int postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepository.delete(post);
		
	}

	@Override
	public List<PostDto> getPostsByCategory(int catId) {
		Category category=this.categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", catId));
		System.out.println("category :"+category.getCategoryId());
		List<Post> posts = this.postRepository.findByCategory(category);
		System.out.println("category :"+category.getCategoryId());
		List<PostDto> postDto =posts.stream().map((post)-> this.mapper.map(post, 
				PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(Long userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts=this.postRepository.findByUser(user);
		List<PostDto> postDto=posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepository.findByTitleContaining(keyword);
		List<PostDto> postDto = posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

}
