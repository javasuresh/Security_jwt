package com.security.app.service;

import java.util.List;

import com.security.app.paylods.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto category);
	CategoryDto updateCategory(CategoryDto category,int categoryId);
	CategoryDto getCategoryById(int categoryId);
	List<CategoryDto> getAllCatergory();
	void deleteCategory(int categoryId);
	
	

}
