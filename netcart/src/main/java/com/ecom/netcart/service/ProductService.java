package com.ecom.netcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.netcart.entity.Product;
import com.ecom.netcart.exception.CategoryNotFoundException;
import com.ecom.netcart.exception.ProductNotFoundException;
import com.ecom.netcart.model.ProductOutputModel;
import com.ecom.netcart.repository.ProductDao;

@Service
public interface ProductService {
	
	public ProductOutputModel getProductByName(String name) throws ProductNotFoundException;
	public ProductOutputModel getProductById(Integer pId) throws ProductNotFoundException;
	public List<ProductOutputModel> getAllProducts();
}
