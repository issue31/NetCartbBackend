package com.ecom.netcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.netcart.entity.Category;
import com.ecom.netcart.exception.CategoryNotFoundException;
import com.ecom.netcart.model.CategoryOutputModel;
import com.ecom.netcart.repository.CategoryDao;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao categoryDao;
	public CategoryOutputModel getCategoryByName(String name) throws CategoryNotFoundException {
		Category category = categoryDao.findByName(name);
		if(category!=null)
		{
			CategoryOutputModel categoryOutputModel = new CategoryOutputModel();
			categoryOutputModel.setCategoryDescription(category.getCategoryDescription());
			categoryOutputModel.setCategoryTitle(category.getCategoryTitle());
			return categoryOutputModel;
		}
		else
		{
			throw new CategoryNotFoundException("The category is not available");
		}
	}
	public List<CategoryOutputModel> getAllCategories(){
		List<CategoryOutputModel> categoryOutputModels=new ArrayList<>();
		List<Category> categories=categoryDao.findAll();
		for(Category c:categories) {
			CategoryOutputModel categoryOutputModel = new CategoryOutputModel();
			categoryOutputModel.setCategoryDescription(c.getCategoryDescription());
			categoryOutputModel.setCategoryTitle(c.getCategoryTitle());
			categoryOutputModels.add(categoryOutputModel);
		}
		return categoryOutputModels;
		}

}
