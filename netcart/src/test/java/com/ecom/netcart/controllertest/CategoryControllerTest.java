package com.ecom.netcart.controllertest;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecom.netcart.controller.CategoryController;
import com.ecom.netcart.exception.CategoryNotFoundException;
import com.ecom.netcart.model.CategoryOutputModel;
import com.ecom.netcart.service.CategoryServiceImpl;

@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {
	
	@Mock
	private CategoryServiceImpl categoryService;
	
	@InjectMocks
	private CategoryController categoryController;
	
	private MockMvc mockMvc;
	
	@Test
	public void testGetCategoryByName() throws Exception {
		// Arrange
		String categoryName = "Electronics";
		CategoryOutputModel categoryOutputModel = new CategoryOutputModel();
		categoryOutputModel.setCategoryTitle(categoryName);
		categoryOutputModel.setCategoryDescription("Electronic Products");

		when(categoryService.getCategoryByName(categoryName)).thenReturn(categoryOutputModel);

		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

		// Act & Assert
		mockMvc.perform(get("/category").param("name", categoryName))
			.andExpect(status().isOk());
		// You can add more assertions based on the expected response
	}
	
	@Test
	public void testGetCategoryByName_CategoryNotFound() throws Exception {
		// Arrange
		String categoryName = "InvalidCategory";
		
		when(categoryService.getCategoryByName(categoryName)).thenThrow(CategoryNotFoundException.class);

		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

		// Act & Assert
		mockMvc.perform(get("/category").param("name", categoryName))
			.andExpect(status().isNotFound());
		// You can add more assertions based on the expected response
	}
	
	@Test
	public void testGetAllCategories() throws Exception {
		// Arrange
		List<CategoryOutputModel> categoryOutputModels = new ArrayList<>();
		CategoryOutputModel category1 = new CategoryOutputModel();
		category1.setCategoryTitle("Category1");
		category1.setCategoryDescription("Category 1 Description");
		categoryOutputModels.add(category1);
		// Add more categories as needed

		when(categoryService.getAllCategories()).thenReturn(categoryOutputModels);

		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

		// Act & Assert
		mockMvc.perform(get("/getallcategory"))
			.andExpect(status().isOk());
		// You can add more assertions based on the expected response
	}
}
