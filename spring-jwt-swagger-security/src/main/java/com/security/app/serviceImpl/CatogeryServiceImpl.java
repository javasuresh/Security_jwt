package com.security.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.app.entity.Category;
import com.security.app.exception.ResourceNotFoundException;
import com.security.app.paylods.CategoryDto;
import com.security.app.repository.CategoryRepository;
import com.security.app.service.CategoryService;

@Service
public class CatogeryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto category) {
		Category categorys=this.dtoToCategory(category);
		Category saveCategory=this.categoryRepository.save(categorys);
		return this.categoryToDto(saveCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, int categoryId) {
		Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		
		cat.setCategoryTitle(category.getCategoryTitle());
		cat.setCategoryDescription(category.getCategoryDescription());
		
		Category save = this.categoryRepository.save(cat);
		CategoryDto categoryToDto = this.categoryToDto(save);
		return categoryToDto;
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		
		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCatergory() {
		List<Category> category = this.categoryRepository.findAll();
		List<CategoryDto> categoryDto=category.stream().map(categorys->this.categoryToDto(categorys)).collect(Collectors.toList());
		return categoryDto;
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		this.categoryRepository.delete(category);
		
	}

	private Category dtoToCategory(CategoryDto category) {
		Category categorys = this.mapper.map(category, Category.class);
		
		/*
		 User users=new User();
		 users.setId(user.getId());
		users.setName(user.getName());
		users.setEmail(user.getEmail());
		users.setPassword(user.getPassword());
		users.setAbout(user.getAbout());*/
		return categorys;
		
	}
	
	private CategoryDto categoryToDto(Category category) {
		CategoryDto categorys = this.mapper.map(category, CategoryDto.class);
		/*UserDto users=new UserDto();
		users.setId(user.getId());
		users.setName(user.getName());
		users.setEmail(user.getEmail());
		users.setPassword(user.getPassword());
		users.setAbout(user.getAbout());*/
		return categorys;
		
	}
}
