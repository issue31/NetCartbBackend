package com.ecom.netcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.netcart.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
	@Query("select c from Category c where c.categoryTitle = :name")
	Category findByName(String name);

	
}
