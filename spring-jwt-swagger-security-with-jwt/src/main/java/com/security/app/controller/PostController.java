package com.security.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.security.app.constants.AppConstants;
import com.security.app.paylods.ApiResponse;
import com.security.app.paylods.PostDto;
import com.security.app.paylods.PostResponse;
import com.security.app.service.FileService;
import com.security.app.service.PostService;

//import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;

	@PostMapping("/post/user/{userId}/category/{catId}")
	public ResponseEntity<PostDto> createCategory(@RequestBody PostDto postDto,
			@PathVariable("userId") Long userId,
			@PathVariable("catId") int catId){
		System.out.println(postDto);
		PostDto postDtos = this.postService.createPost(postDto,userId,catId);
		System.out.println(postDtos);
		return new ResponseEntity<>(postDtos, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/post/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Long userId){
		List<PostDto> posts=this.postService.getPostByUser(userId);
	
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/post/category/{catId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("catId") int catId){
		List<PostDto> posts=this.postService.getPostsByCategory(catId);
	
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updateCategory(@RequestBody PostDto postDto,@PathVariable("postId") int postId){
		PostDto updateUser = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updateUser);
	}
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") int postId){
		this.postService.deletePost(postId);
		return new ResponseEntity(new ApiResponse("Post is deleted successfully", true),HttpStatus.OK);
		
	}
	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
			){
		PostResponse allPosts = this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(allPosts,HttpStatus.OK);
	}
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") int postId){
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/post/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
	List<PostDto> searchPosts = this.postService.searchPosts(keywords);
	return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
	}
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
	
	postDto.setImageName(fileName);
	PostDto updatePost = this.postService.updatePost(postDto, postId);
	return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	@GetMapping("/post/image/download/{imageName}")
	public void downloadPostImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
			) throws IOException{
		
		InputStream res=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(res, response.getOutputStream());
		
		
		}
	
}
