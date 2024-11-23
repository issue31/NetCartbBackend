package com.ecom.netcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.ecom.netcart.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{
	@Query("select p from Product p where p.pName = :name")

	Product findByName(String name);
}
