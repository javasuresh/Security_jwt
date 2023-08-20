package com.security.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.paylods.ApiResponse;
import com.security.app.paylods.CategoryDto;
import com.security.app.paylods.UserDto;
import com.security.app.service.CategoryService;
import com.security.app.service.UserService;

@RestController
@RequestMapping("/api/categorys")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/category")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		System.out.println(categoryDto);
		CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
		System.out.println(createCategoryDto);
		return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
		
	}
	@PutMapping("/categorys/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("categoryId") int categoryId){
		CategoryDto updateUser = this.categoryService.updateCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updateUser);
	}
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity(new ApiResponse("Category deleted successfully", true),HttpStatus.OK);
		
	}
	@GetMapping("/categorys")
	public ResponseEntity<List<CategoryDto>> getAllCategorys(){
		return ResponseEntity.ok(this.categoryService.getAllCatergory());
	}
	@GetMapping("/categorys/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") int categoryId){
		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
		
	}
	
}
