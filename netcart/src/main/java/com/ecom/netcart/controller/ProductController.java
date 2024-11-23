package com.ecom.netcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.netcart.exception.ProductNotFoundException;
import com.ecom.netcart.model.ProductOutputModel;
import com.ecom.netcart.service.ProductServiceImpl;

@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	ProductServiceImpl productService;
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	@GetMapping("/findproduct/{name}")
	public ResponseEntity<ProductOutputModel> getProductByName(@PathVariable("name") String name) throws ProductNotFoundException{
		logger.info("getProductByName() method started");
		ProductOutputModel model = productService.getProductByName(name);
		logger.info("getProductByName={}",model);
		logger.info("getProductByName() method ended");
		return new ResponseEntity<ProductOutputModel>(model,HttpStatus.OK);
	}
	@GetMapping("/findbyid")
	public ResponseEntity<ProductOutputModel> getProductById(Integer pId) throws ProductNotFoundException{
		logger.info("getProductById() method started");
		ProductOutputModel products = productService.getProductById(pId);
		logger.info("getProductById={}",products);
		logger.info("getProductById() method ended");
		return new ResponseEntity<ProductOutputModel>(products,HttpStatus.OK);
	}
	@GetMapping("/getallproduct")
	public ResponseEntity<List<ProductOutputModel>> getAllProducts(){
		logger.info("getAllProducs() method started");
		List<ProductOutputModel> outputModel = productService.getAllProducts();
		logger.info("getAllProducs={}",outputModel);
		logger.info("getAllProducs() method ended");
		return new ResponseEntity<List<ProductOutputModel>>(outputModel,HttpStatus.OK);
	}
}
