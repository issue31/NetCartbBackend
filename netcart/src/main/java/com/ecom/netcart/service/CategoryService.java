package com.ecom.netcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.netcart.entity.Category;
import com.ecom.netcart.exception.CategoryNotFoundException;
import com.ecom.netcart.exception.ProductNotFoundException;
import com.ecom.netcart.model.CategoryOutputModel;
import com.ecom.netcart.model.ProductOutputModel;
import com.ecom.netcart.repository.CategoryDao;

@Service
public interface CategoryService {
	public CategoryOutputModel getCategoryByName(String name) throws CategoryNotFoundException;
	public List<CategoryOutputModel> getAllCategories();

}
