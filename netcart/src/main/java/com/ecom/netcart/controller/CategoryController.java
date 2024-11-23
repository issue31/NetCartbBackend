package com.ecom.netcart.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.netcart.exception.CategoryNotFoundException;
import com.ecom.netcart.model.CategoryOutputModel;
import com.ecom.netcart.service.CategoryServiceImpl;

@RestController
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl categoryService;
	
	//get all products belonging to a particular  category by using category name
	Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@GetMapping("/category")
	public ResponseEntity<CategoryOutputModel> getCategoryByName(@RequestParam String name) throws CategoryNotFoundException {
		logger.info("getCategoryByName() method started");
		CategoryOutputModel categoryOutputModel = categoryService.getCategoryByName(name);
		logger.info("getCategoryByName={}",categoryOutputModel);
		logger.info("getCategoryByName() method ended");
		return new ResponseEntity<CategoryOutputModel>(categoryOutputModel,HttpStatus.OK);
	}
	
	@GetMapping("/getallcategory")
	public ResponseEntity<List<CategoryOutputModel>>  getAllCategories(){
		logger.info("getAllCategories() method started");
		List<CategoryOutputModel> categoryOutputModels = categoryService.getAllCategories();
		logger.info("getAllCategories={}",categoryOutputModels);
		logger.info("getAllCategories() method ended");
		return  new ResponseEntity<List<CategoryOutputModel>>(categoryOutputModels,HttpStatus.OK);
	
}
}
