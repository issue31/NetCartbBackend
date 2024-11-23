package com.ecom.netcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.netcart.entity.Product;
import com.ecom.netcart.exception.ProductNotFoundException;
import com.ecom.netcart.model.ProductOutputModel;
import com.ecom.netcart.repository.ProductDao;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao productDao;
	public ProductOutputModel getProductByName(String name) throws ProductNotFoundException  {
		Product product = productDao.findByName(name);
		if(product!=null)
		{
			ProductOutputModel productOutputModel = new ProductOutputModel();
			productOutputModel.setPDesc(product.getPDesc());
			productOutputModel.setPId(product.getPId());
			productOutputModel.setPName(product.getPName());
			productOutputModel.setPPrice(product.getPPrice());
			//productOutputModel.setPQuantity(product.getPQuantity());
			return productOutputModel;
		}
		else
		{
			throw new ProductNotFoundException("The product is not available");
		}
	}
	public ProductOutputModel getProductById(Integer pId) throws ProductNotFoundException {
		Product product = productDao.findById(pId).orElse(null);
		if(product!=null) {
			ProductOutputModel productOutputModel = new ProductOutputModel();
			productOutputModel.setPDesc(product.getPDesc());
			productOutputModel.setPId(product.getPId());
			productOutputModel.setPName(product.getPName());
			productOutputModel.setPPrice(product.getPPrice());
			//productOutputModel.setPQuantity(product.getPQuantity());
			return productOutputModel;
		}
		else
		{
			throw new ProductNotFoundException("The product is not available");
		}
	}
	public List<ProductOutputModel> getAllProducts(){
		List<ProductOutputModel> outputModels = new ArrayList<ProductOutputModel>();
		List<Product> products = productDao.findAll();
		for(Product p:products) {
			ProductOutputModel model = new ProductOutputModel();
			model.setPDesc(p.getPDesc());
			model.setPId(p.getPId());
			model.setPName(p.getPName());
			model.setPPrice(p.getPPrice());
			//model.setPQuantity(p.getPQuantity());
			outputModels.add(model);
		}
		return outputModels;
	}
}
